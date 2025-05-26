package org.learning.di;

import org.learning.di.annotation.Component;
import org.learning.di.annotation.Primary;
import org.learning.di.error.BeanCreationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationContext {
    private final HashMap<String, Object> cache;

    private ApplicationContext(HashMap<String, Object> cache) {
        this.cache = cache;
    }

    public static ApplicationContext getInstance() {
        ApplicationContext applicationContext = new ApplicationContext(new HashMap<>());
        applicationContext.init();
        return applicationContext;
    }

    private void init() {
        Collection<Class<?>> classes = ClassScanner.getAllComponentClasses("");
        for (Class<?> aClass : classes) {
            get(aClass, classes);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T get(Class<T> clz, Collection<Class<?>> classes) {
        if (this.cache.containsKey(clz.getName())) {
            System.out.println("Returning " + clz.getName() + " from the cache");
            return (T) this.cache.get(clz.getName());
        }

        if (!clz.isAnnotationPresent(Component.class) && !clz.isInterface()) {
            throw BeanCreationError.notAComponent(clz);
        }

        if (clz.isInterface()) {
            Class<T> implementationClass = findImplementationOf(clz, classes);
            T classInstance = createInstanceOf(implementationClass, classes);
            this.cache.put(clz.getName(), classInstance);
            return classInstance;
        }

        return createInstanceOf(clz, classes);
    }

    @SuppressWarnings("unchecked")
    private <T> T createInstanceOf(Class<T> clz, Collection<Class<?>> classes) {
        System.out.println("Creating instance of clz " + clz.getName());
        Constructor<?> constructor = clz.getConstructors()[0];
        Parameter[] parameters = constructor.getParameters();

        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            args[i] = get(parameters[i].getType(), classes);
        }

        try {
            T t = (T) constructor.newInstance(args);
            this.cache.put(clz.getName(), t);
            return t;
        } catch (Throwable e) {
            String message = "Unable to create instance of " + clz.getName();
            System.out.println("Error : " + message);
            throw new RuntimeException(message);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> findImplementationOf(Class<T> clz, Collection<Class<?>> classes) {
        List<Class<?>> implementationClasses = classes.stream()
                .filter(clz::isAssignableFrom)
                .collect(Collectors.toList());

        if (implementationClasses.isEmpty()) {
            throw BeanCreationError.noImplementationFoundFor(clz);
        }

        if (implementationClasses.size() == 1) {
            return (Class<T>) implementationClasses.get(0);
        }

        List<Class<?>> primaryClasses = implementationClasses.stream()
                .filter(aClass -> aClass.isAnnotationPresent(Primary.class))
                .collect(Collectors.toList());

        if (primaryClasses.size() > 1) {
            throw BeanCreationError.moreThanOnePrimaryImplementationPresent(clz, primaryClasses);
        }

        if(primaryClasses.isEmpty()) {
            throw BeanCreationError.noPrimaryImplementationFound(clz, implementationClasses);
        }

        return (Class<T>) primaryClasses.get(0);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeat(Class<T> beanClazz) {
        return (T) this.cache.get(beanClazz.getName());
    }
}

package org.learning.di;

import org.learning.di.annotation.Component;
import org.learning.di.error.BeanCreationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;

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
            get(aClass);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T get(Class<T> clz)  {
        if (this.cache.containsKey(clz.getName())) {
            System.out.println("Returning " + clz.getName() + " from the cache");
            return (T) this.cache.get(clz.getName());
        }

        if (!clz.isAnnotationPresent(Component.class)) {
            throw BeanCreationError.notAComponent(clz);
        }

        System.out.println("Creating instance of clz " + clz.getName());
        Constructor<?> constructor = clz.getConstructors()[0];
        Parameter[] parameters = constructor.getParameters();

        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            args[i] = get(parameters[i].getType());
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
    public <T> T getBeat(Class<T> beanClazz) {
        return (T) this.cache.get(beanClazz.getName());
    }
}

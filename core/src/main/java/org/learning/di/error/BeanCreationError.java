package org.learning.di.error;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCreationError extends RuntimeException {
    public BeanCreationError(String msg) {
        super(msg);
    }

    public static <T> BeanCreationError notAComponent(Class<T> clz) {
        String msg = String.format("Cannot create instance of %s, to create instance mark it as a component using @Component", clz.getName());
        return new BeanCreationError(msg);
    }

    public static <T> BeanCreationError noImplementationFoundFor(Class<T> clz) {
        String msg = String.format("No concrete implementation of %s present.", clz.getName());
        return new BeanCreationError(msg);
    }

    public static <T> BeanCreationError moreThanOnePrimaryImplementationPresent(Class<T> clz, List<Class<?>> primaryClasses) {
        String implementationClassesNames = primaryClasses.stream().map(Class::getName).collect(Collectors.joining(","));
        String msg = String.format(
                "Unable to create instance of %s, multiple primary implementations found %s",
                clz,
                implementationClassesNames
        );

        return new BeanCreationError(msg);
    }

    public static <T> BeanCreationError noPrimaryImplementationFound(Class<T> clz, List<Class<?>> implementationClasses) {
        String implementationClassesNames = implementationClasses.stream().map(Class::getName).collect(Collectors.joining(","));
        String msg = String.format(
                "Unable to create instance of %s, multiple concrete implementation found %s, but no Primary instance, mention one of them as primary using @Primary",
                clz,
                implementationClassesNames
        );

        return new BeanCreationError(msg);
    }
}

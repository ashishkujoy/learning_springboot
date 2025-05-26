package org.learning.di.error;

public class BeanCreationError extends RuntimeException {
    public BeanCreationError(String msg) {
        super(msg);
    }

    public static <T> BeanCreationError notAComponent(Class<T> clz) {
        String msg = String.format("Cannot create instance of %s, to create instance mark it as a component using @Component", clz.getName());
        return new BeanCreationError(msg);
    }
}

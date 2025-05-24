package org.learning.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ApplicationContext {
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clz) {
        System.out.println("Creating instance of clz " + clz.getName());
        Constructor<?> constructor = clz.getConstructors()[0];
        Parameter[] parameters = constructor.getParameters();

        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            args[i] = get(parameters[i].getType());
        }

        try {
            return (T) constructor.newInstance(args);
        } catch (Throwable e) {
            String message = "Unable to create instance of " + clz.getName();
            System.out.println("Error : " + message);
            throw new RuntimeException(message);
        }
    }
}

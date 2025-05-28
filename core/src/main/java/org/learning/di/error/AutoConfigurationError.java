package org.learning.di.error;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AutoConfigurationError extends RuntimeException {
    public AutoConfigurationError(String msg) {
        super(msg);
    }

    public static AutoConfigurationError invalidCountOfConfigureMethods(Class<?> aClass, ArrayList<Method> methods) {
        String msg = String.format(
                "Configuration class %s has %d @Configure methods, expected 1. %s",
                aClass.getName(),
                methods.size(),
                methods.stream().map(Method::getName).collect(Collectors.joining(", "))
        );
        return new AutoConfigurationError(msg);
    }

    public static AutoConfigurationError errorWhileInvokingConfigureMethod(Class<?> aClass, Method method, Throwable throwable) {
        String msg = String.format(
                "Error while invoking @Configure method %s of class %s. Msg: %s",
                method.getName(),
                aClass.getName(),
                throwable.getMessage()
        );
        return new AutoConfigurationError(msg);
    }
}

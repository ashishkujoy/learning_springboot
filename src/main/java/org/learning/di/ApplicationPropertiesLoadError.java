package org.learning.di;

public class ApplicationPropertiesLoadError extends RuntimeException {
    public ApplicationPropertiesLoadError(String msg) {
        super(msg);
    }
}

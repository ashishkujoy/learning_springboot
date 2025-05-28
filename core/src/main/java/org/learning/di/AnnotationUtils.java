package org.learning.di;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AnnotationUtils {
    public static boolean isAnnotatedWith(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        if (clazz.isAnnotationPresent(annotationClass)) return true;

        Collection<Annotation> annotations = addAllAnnotations(clazz);
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationClass)) {
                return true;
            }
        }

        return false;
    }

    private static Collection<Annotation> addAllAnnotations(Class<?> clazz) {
        Set<Annotation> annotations = new HashSet<>();
        for (Annotation annotation : clazz.getAnnotations()) {
            addAllAnnotations(annotation, annotations);
        }
        return annotations;
    }

    private static void addAllAnnotations(Annotation rootAnnotation, Set<Annotation> annotations) {
        if (annotations.contains(rootAnnotation)) {
            return;
        }
        annotations.add(rootAnnotation);
        if (rootAnnotation.annotationType().isAnnotation()) {
            for (Annotation annotation : rootAnnotation.annotationType().getAnnotations()) {
                addAllAnnotations(annotation, annotations);
            }
        }
    }
}

package org.learning.di;

import org.burningwave.core.assembler.ComponentContainer;
import org.burningwave.core.classes.ClassCriteria;
import org.burningwave.core.classes.ClassHunter;
import org.burningwave.core.classes.SearchConfig;
import org.burningwave.core.io.PathHelper;
import org.learning.di.annotation.Component;

import java.util.Collection;

public class ClassScanner {
    public static Collection<Class<?>> getAllComponentClasses(String rootPath) {
        ComponentContainer container = ComponentContainer.getInstance();
        PathHelper pathHelper = container.getPathHelper();
        ClassHunter classHunter = container.getClassHunter();

        SearchConfig searchConfig = SearchConfig.forPaths(pathHelper.getMainClassPaths())
                .by(ClassCriteria.create().allThoseThatMatch(cls ->
                        AnnotationUtils.isAnnotatedWith(cls, Component.class)
                ));

        return classHunter.findBy(searchConfig).getClasses();
    }
}

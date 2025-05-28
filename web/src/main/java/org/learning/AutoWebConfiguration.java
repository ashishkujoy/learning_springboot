package org.learning;

import org.learning.di.annotation.AutoConfiguration;
import org.learning.di.annotation.Configure;

import java.util.Collection;

@AutoConfiguration
public class AutoWebConfiguration {
    @Configure
    public void configure(Collection<Object> beans) {
        System.out.println("=================Auto Web Configuration========================");
        for (Object bean : beans) {
            System.out.println(bean.getClass().getName());
        }
        System.out.println("================Auto Web Configuration=========================");
    }
}

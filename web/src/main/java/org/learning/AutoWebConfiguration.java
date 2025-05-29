package org.learning;

import io.javalin.Javalin;
import org.learning.annotations.GetMapping;
import org.learning.annotations.RestController;
import org.learning.di.annotation.AutoConfiguration;
import org.learning.di.annotation.Configure;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@AutoConfiguration
public class AutoWebConfiguration {
    @Configure
    public void configure(Collection<Object> beans) {
        System.out.println("AutoWebConfiguration: Configuring Javalin application...");
        Javalin app = Javalin.create();
        Stream<Object> objectStream = beans.stream()
                .filter(bean -> bean.getClass().isAnnotationPresent(RestController.class));
        List<Object> controllers = objectStream.toList();
        System.out.println("Found " + controllers.size() + " REST controllers.");
        controllers.forEach(bean -> registerRouter(app, bean));
        app.start(8080);
    }

    private void registerRouter(Javalin app, Object bean) {
        System.out.println("Registering router for: " + bean.getClass().getName());
        String rootPath = bean.getClass().getAnnotation(RestController.class).value();

        Arrays.stream(bean.getClass().getMethods()).filter(method -> method.isAnnotationPresent(GetMapping.class))
                .forEach(method -> {
                    String path = method.getAnnotation(GetMapping.class).value();
                    String fullPath = rootPath + path;
                    app.get(fullPath, ctx -> {
                        try {
                            Object result = method.invoke(bean);
                            ctx.result(result.toString());
                        } catch (Exception e) {
                            ctx.status(500).result("Internal Server Error: " + e.getMessage());
                        }
                    });
                });
    }
}

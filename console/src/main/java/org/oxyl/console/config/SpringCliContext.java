package org.oxyl.console.config;

import org.oxyl.persistence.config.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringCliContext {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(DataSource.class);

    public static ApplicationContext getContext() {
        return context;
    }
}



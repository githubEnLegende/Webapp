package org.oxyl.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationContext {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

    public static ApplicationContext getContext() {
        return context;
    }
}



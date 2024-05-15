package org.oxyl.context;


import org.oxyl.persistence.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Context {

    private static Context instance;
    private static ApplicationContext context = new AnnotationConfigApplicationContext(DataSource.class);

    private Context(){}

    public static Context getInstance(){
        if(instance == null){
            instance = new Context();
        }
        return instance;
    }

    public ApplicationContext getContext(){
        return context;
    }

}

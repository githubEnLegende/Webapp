package org.oxyl.webapp.config;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

//        servletContext.addListener(new ContextLoaderListener(context));
        DispatcherServlet servlet = new DispatcherServlet(context);
        context.register(WebConfig.class);
//        context.setServletContext(servletContext);
//        context.refresh();
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("dashboard", servlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}

package org.oxyl.webapp.config;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        DispatcherServlet servlet = new DispatcherServlet(context);
        context.register(WebConfig.class);

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("dashboard", servlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");

        var securityFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
        securityFilter.addMappingForUrlPatterns(null, false, "/*");

    }
}

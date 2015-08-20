package com.sample.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.List;

/**
 @EnableJpaRepositories("com.sample.dao")
 */
public abstract class Initializer extends WebMvcConfigurationSupport {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    public abstract List<Class> getRegisterClass();

    public abstract List<String> getMappedUrl();

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        for (Class aClass : getRegisterClass()) {
            ctx.register(aClass);
        }

        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        for (String s : getMappedUrl()) {
            servlet.addMapping(s);
        }

        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);
    }
}

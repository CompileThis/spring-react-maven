package com.compilethis.srm;

import com.compilethis.srm.application.ApplicationConfiguration;
import com.compilethis.srm.service.ServiceDispatcherConfiguration;
import com.compilethis.srm.web.WebDispatcherConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ContainerInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfiguration.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext webDispatcherServlet = new AnnotationConfigWebApplicationContext();
        webDispatcherServlet.register(WebDispatcherConfiguration.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic webDispatcher = container.addServlet("web-dispatcher", new DispatcherServlet(webDispatcherServlet));
        webDispatcher.setLoadOnStartup(1);
        webDispatcher.addMapping("/");

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext serviceDispatcherServlet = new AnnotationConfigWebApplicationContext();
        webDispatcherServlet.register(ServiceDispatcherConfiguration.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic serviceDispatcher = container.addServlet("service-dispatcher", new DispatcherServlet(serviceDispatcherServlet));
        serviceDispatcher.setLoadOnStartup(2);
        serviceDispatcher.addMapping("/service/*");
    }
}

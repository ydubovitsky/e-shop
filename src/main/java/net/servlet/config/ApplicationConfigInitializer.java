package net.servlet.config;

import net.servlet.configuration.JavaConfigServlet;
import net.servlet.configuration.JavaConfigServletT;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * For this to work, you need to create a file along the path : META-INF/services/javax.servlet.ServletContainerInitializer
 */
public class ApplicationConfigInitializer implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        // Явным образом создаем объект
        JavaConfigServlet servlet = new JavaConfigServlet();
        ServletRegistration.Dynamic servletConfig = ctx.addServlet("JavaConfigServlet", servlet);
        servletConfig.addMapping("/java");
        // или можно так:
        ServletRegistration.Dynamic servletConfigT = ctx.addServlet("JavaConfigServletT", JavaConfigServletT.class);
        servletConfigT.addMapping("/java2");

    }
}

package net.config;

import net.servlet.JavaConfigServlet;

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
        JavaConfigServlet servlet = new JavaConfigServlet();
        ServletRegistration.Dynamic servletConfig = ctx.addServlet("JavaConfigServlet", servlet);
        servletConfig.addMapping("/java");
    }
}

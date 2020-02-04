package net.config;

import net.filter.ClassConfigFilter;
import net.servlet.configuration.JavaConfigServlet;
import net.servlet.configuration.JavaConfigServletT;

import javax.servlet.*;
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
        servletConfig = ctx.addServlet("JavaConfigServletT", JavaConfigServletT.class);
        servletConfig.addMapping("/java2");
        // filter config
        ClassConfigFilter configFilter = new ClassConfigFilter();
        FilterRegistration.Dynamic filterRegistration = ctx.addFilter("ClassConfigFilter", configFilter);
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");

//        Так делать не обязательно!
//        ServletRegistration.Dynamic servletConfigT = ctx.addServlet("JavaConfigServletT", JavaConfigServletT.class);
//        servletConfigT.addMapping();
    }
}

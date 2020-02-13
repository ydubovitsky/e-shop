package shop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shop.constants.Constants;
import shop.service.impl.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class); //? какой логур тут используется? зачем нам ch.qos.logback?

    private ServiceManager serviceManager;



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
            sce.getServletContext().setAttribute(Constants.CATEGORY_LIST, serviceManager.getProductService().listAllCategory());
            sce.getServletContext().setAttribute(Constants.PRODUCER_LIST, serviceManager.getProductService().listAllProducer());
        } catch (Exception e) {
            LOGGER.error("Web-application init failed " + e.getMessage(), e);
            throw e;
        }
        LOGGER.info("Web-application initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Web-application destroyed");

    }
}

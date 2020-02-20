package net.shop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import net.shop.entity.impl.Category;
import net.shop.entity.impl.Producer;
import net.shop.service.impl.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.shop.Constants.CATEGORY_LIST;
import static net.shop.Constants.PRODUCERS_LIST;

@WebListener
public class EShopApplicationListener implements ServletContextListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(EShopApplicationListener.class);
	private ServiceManager serviceManager;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			//! В этом блоке кода инициализируется serviceManager когда приложение стартует
			serviceManager = ServiceManager.getInstance(sce.getServletContext());
			//! Тут уже создаются объекты которые тоже инициализируются когда приложение стартует, таким образом к этим мы устанавливаем значения атрибутов, кторые будут доступны в контексте Сервлета
			List<Producer> producers = serviceManager.getProductService().listAllProducers();
			sce.getServletContext().setAttribute(PRODUCERS_LIST, producers);
			List<Category> categories = serviceManager.getProductService().listAllCategories();
			sce.getServletContext().setAttribute(CATEGORY_LIST, categories);
		} catch (RuntimeException e) {
			LOGGER.error("Web application 'ishop' init failed: "+e.getMessage(), e);
			throw e;
		}
		LOGGER.info("Web application 'ishop' initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		serviceManager.close();
		LOGGER.info("Web application 'ishop' destroyed");
	}
}

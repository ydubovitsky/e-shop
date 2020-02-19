package net.shop.service.impl;

import net.shop.service.OrderService;
import net.shop.service.ProductService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);

	/**
	 * Создаем объект класса через Синглтон
	 * @param context - контекст приложения
	 * @return
	 */
	public static ServiceManager getInstance(ServletContext context) {
		ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
		if (instance == null) {
			instance = new ServiceManager(context);
			context.setAttribute("SERVICE_MANAGER", instance);
		}
		return instance;
	}

	/**
	 * Getters
	 * @return
	 */
	public ProductService getProductService() {
		return productService;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public String getApplicationProperty(String key) {
		return applicationProperties.getProperty(key);
	}

	/**
	 * Close DataSource
	 */
	public void close() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			LOGGER.error("Close dataSource failed " + e.getMessage(), e);
		}
	}

	private final Properties applicationProperties = new Properties();
	private final BasicDataSource dataSource;
	private final ProductService productService;
	private final OrderService orderService;

	/**
	 * Constructor
	 * @param context
	 */
	private ServiceManager(ServletContext context) {
		loadApplicationProperties();
		dataSource =  createDataSource();
		productService = new ProductServiceImpl(dataSource);
		orderService = new OrderServiceImpl();
	}

	/**
	 * Create and init DataSource
	 * @return
	 */
	private BasicDataSource createDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDefaultAutoCommit(false);
		dataSource.setRollbackOnReturn(true); //* когда возврат соединения в пул, если мы не сделали комит, то RollBack
		dataSource.setUrl(getApplicationProperty("db.url"));
		dataSource.setUsername(getApplicationProperty("db.username"));
		dataSource.setPassword(getApplicationProperty("db.password"));
		dataSource.setInitialSize(Integer.valueOf(getApplicationProperty("db.pool.initSize")));
		dataSource.setMaxTotal(Integer.valueOf(getApplicationProperty("db.pool.maxSize")));
		dataSource.setDriverClassName(getApplicationProperty("db.driver"));
		return dataSource;
	}

	/**
	 * Load all properties from file.
	 */
	private void loadApplicationProperties(){
		try(InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
			applicationProperties.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

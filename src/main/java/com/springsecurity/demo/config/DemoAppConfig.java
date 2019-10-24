package com.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	//setup a variable to hold the properties
	@Autowired
	private Environment environment;
	
	//set up a logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	//define a bean for security data source
	@Bean
	public DataSource securityDataSource() {
		
		//create a connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		//set the JDBC driver class
		try {
			securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));	
		} catch(PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		
		//log the connection properties
		logger.info("jdbc.url= " +environment.getProperty("jdbc.url"));
		logger.info("jdbc.user= " +environment.getProperty("jdbc.user"));
		
		//set database connection properties
		securityDataSource.setUser(environment.getProperty("jdbc.user"));
		securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		securityDataSource.setPassword(environment.getProperty("jdbc.password"));
		
		//set connection pool properties
		securityDataSource.setInitialPoolSize(getIntegerPropertyValue("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntegerPropertyValue("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntegerPropertyValue("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntegerPropertyValue("connection.pool.maxIdleTime"));
		
		return securityDataSource;
		
	}
	
	private int getIntegerPropertyValue(String property) {
		return Integer.parseInt(environment.getProperty(property));
	}
}










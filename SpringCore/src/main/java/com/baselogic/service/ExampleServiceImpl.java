package com.baselogic.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.baselogic.dao.OrderDAO;
import com.baselogic.util.ExampleUtils;

/**
 * {@link ExampleService}
 */
@Component("exampleService")
//@Component would create bean "exampleServiceImpl"
public class ExampleServiceImpl implements ExampleService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExampleServiceImpl.class);

	@Autowired
	private OrderDAO orderDao;

	@Autowired
	private OrderDAO aliasedDao;
	
	@Autowired
	private String message;
	
	List<String> initializationOrder = new ArrayList<String>();

	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		return message;	
	}
	
	public void setMessage(String msg){
		this.message = msg;
	}
	
	@PostConstruct
	public void postConstruct(){
        if (orderDao != null) {
            logger.debug("MY DEP BEAN {}", orderDao.toString());
        }
        logger.debug("> pc > {}: @PostConstruct <<<<<", this.getClass());
        initializationOrder.add("postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		logger.debug("> pd > {}: @PreDestroy <<<<<", this.getClass());
		initializationOrder.add("preDestroy");
	}
	
}

package com.baselogic.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * ComponentBeanFactoryPostProcessor
 * 
 * NOTE: A BeanFactoryPostProcessor modifies bean definitions, 
 * while a BeanPostProcessor replaces bean instances (such as when creating a proxy).
 *
 * <p>Spring Certification objective: 1.2</p>
 * 
 * @see http://springcert.sourceforge.net/core-3/index.html#beans
 *
 * @author Mick Knutson
 * <p><a href="http://www.baselogic.com">Blog: http://baselogic.com</a></p>
 * <p><a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a></p>
 * <p><a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a></p>
 * <p><a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a></p>
 * 
 * @since 2012
 * 
 */
@Component
public class ComponentBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ComponentBeanFactoryPostProcessor.class);

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		logger.info(">>> bfpp.ppBf >> postProcessBeanFactory...");
		
		BeanDefinition beanDefinition = beanFactory
				.getBeanDefinition("exampleServiceInitializingBeanImpl");
		
		logger.info(beanDefinition.toString());

		// Register a new Alias
		beanFactory.registerAlias("exampleServiceInitializingBeanImpl",
				"exampleServiceInitializingBeanImplBeanFactoryPostProcessed");
		
		logger.info(">>> bfpp.ppBf >> Can change bean definition here.. for beanDefinition: {}"
						, beanDefinition.toString());
	}
	
}
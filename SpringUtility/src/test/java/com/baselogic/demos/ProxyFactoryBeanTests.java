package com.baselogic.demos;

//Hamcrest
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

//JUnit
//import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//Mockito
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

//PowerMock
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baselogic.common.SimpleBean;
import com.baselogic.domain.Customer;
import com.baselogic.domain.Item;
import com.baselogic.domain.Order;
import com.baselogic.service.ExampleService;
import com.baselogic.service.ExampleServiceInitializingBeanImpl;
import com.baselogic.service.UnImplementedService;

/**
 * ProxyFactoryBeanTests
 *
 * <p>Spring Certification objective: 2.1</p>
 * 
 * @see http://springcert.sourceforge.net/core-3/index.html#aop
 *
 * @author Mick Knutson
 * <p><a href="http://www.baselogic.com">Blog: http://baselogic.com</a></p>
 * <p><a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a></p>
 * <p><a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a></p>
 * <p><a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a></p>
 * 
 * @see http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book
 * @see http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166
 * @see http://baselogic.com
 * @see http://linkedin.com/in/mickknutson
 * @see http://twitter.com/mickknutson
 * @see http://github.com/mickknutson
 * 
 * @since 2012
 * 
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProxyFactoryBeanTests {

	private final Logger logger = LoggerFactory.getLogger(ProxyFactoryBeanTests.class);

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testCustomerWithProxyFactoryService(){
		logger.info(">>>------------------------------------------------->>>");
		
		ExampleService service = applicationContext.getBean("exampleServiceInitializingBeanImplProxy", ExampleService.class);
		
		logger.info("service: {}", service.toString());
		logger.info("service: {}", service.getMessage());

		//SimpleBean simpleBean = ((ExampleServiceInitializingBeanImpl)service).getSimpleBean();
		//logger.info("simpleBean: {}", simpleBean.getMessage());

		//assertThat(service instanceof UnImplementedService, is(true));
		//assertThat(service instanceof ExampleServiceInitializingBeanImpl, is(true));
		//assertThat(service instanceof InitializingBean, is(true));
		//assertThat(service instanceof ExampleService, is(true));
		//assertThat(service instanceof DisposableBean, is(true));
		
	}

}
package com.baselogic.service;

//Static imports
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.baselogic.service.ExampleService;
import com.baselogic.util.ContextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ExampleConfigurationTests
 * 
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
 * 
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 *
 * @author Mick Knutson
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * 
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 7 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 7 Cookbook Amazon</a>
 * 
 * @since 2012
 * 
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleConfigurationTests {
	
	private static final Logger logger = LoggerFactory.getLogger(ExampleConfigurationTests.class);
	
	@Autowired
	private ContextUtils contextUtils;
	
	@Autowired
	@Qualifier("exampleService")
	private ExampleService service;
	
	@Autowired
	private ExampleServiceInitializingBeanImpl exampleServiceInitializingBeanImpl;
	
	@Before
	public void beforeEachUnitTest(){}

	@After
	public void afterEachUnitTest(){}

	@Test
	public void testProxyExampleService() throws Exception {

		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>------------------------------------------------->>>");
		logger.info("--> testProxyExampleService: {}", service.getMessage());
		
		assertThat(service instanceof ExampleService, is(true));
		
		/*assertThat(initializationOrder.size(), is(2));
		assertThat(initializationOrder.get(0), is("postConstruct"));
		assertThat(initializationOrder.get(1), is("afterPropertiesSet"));*/
	}

	@Test
	public void testInitializationSteps() throws Exception {
		List<String> initializationOrder = exampleServiceInitializingBeanImpl.initializationOrder;
		logger.info("--> exampleServiceInitializingBeanImpl: {}", initializationOrder);
		assertThat(initializationOrder.size(), is(2));
		assertThat(initializationOrder.get(0), is("postConstruct"));
		assertThat(initializationOrder.get(1), is("afterPropertiesSet"));
	}

	@Test
	public void testBeansInContext() throws Exception {
		logger.info(contextUtils.showBeansInContext());
		//contextUtils.printBeansInContext();
	}

}

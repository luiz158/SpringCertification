package com.baselogic.demos;

//Static imports

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baselogic.components.PropertyInjectionComponent;
import com.baselogic.domain.Activity;
import com.baselogic.domain.Customer;
import com.baselogic.util.ExampleUtils;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SpelTests
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
public class SimpleTests {
	
	private final Logger logger = LoggerFactory.getLogger(SimpleTests.class);

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testSingletonPropertyInjectionComponent() {
		
		PropertyInjectionComponent propertyInjectionComponent1 = 
				applicationContext.getBean("propertyInjectionComponent", PropertyInjectionComponent.class);

		PropertyInjectionComponent propertyInjectionComponent2 = 
				applicationContext.getBean("propertyInjectionComponent", PropertyInjectionComponent.class);

		logger.info(">>>------------------------------------------------->>>");
		logger.debug("propertyInjectionComponent1: {}", propertyInjectionComponent1.toString());
		logger.debug("propertyInjectionComponent2: {}", propertyInjectionComponent2.toString());
		
		assertThat(propertyInjectionComponent1,
				equalTo(propertyInjectionComponent2));

		assertThat(propertyInjectionComponent1.getSomeDouble(),
				equalTo(propertyInjectionComponent2.getSomeDouble()));
		
		assertThat(propertyInjectionComponent1 instanceof PropertyInjectionComponent,
				is(true));
	}

	
	
	@Test
	public void testPrototypePropertyInjectionComponent() {
		
		PropertyInjectionComponent prototypePropertyInjectionComponent1 = 
				applicationContext.getBean("prototypePropertyInjectionComponent", PropertyInjectionComponent.class);

		PropertyInjectionComponent prototypePropertyInjectionComponent2 = 
				applicationContext.getBean("prototypePropertyInjectionComponent", PropertyInjectionComponent.class);

		logger.info(">>>------------------------------------------------->>>");
		logger.debug("prototypePropertyInjectionComponent1: {}", prototypePropertyInjectionComponent1.toString());
		logger.debug("prototypePropertyInjectionComponent2: {}", prototypePropertyInjectionComponent2.toString());

		assertThat(prototypePropertyInjectionComponent1,
				not(equalTo(prototypePropertyInjectionComponent2)));

		assertThat(prototypePropertyInjectionComponent1.getSomeDouble(),
				not(equalTo(prototypePropertyInjectionComponent2.getSomeDouble())));

		assertThat(prototypePropertyInjectionComponent1 instanceof PropertyInjectionComponent,
				is(true));
	}

}
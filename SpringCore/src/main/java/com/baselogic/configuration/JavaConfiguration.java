package com.baselogic.configuration;

import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.baselogic.domain.Customer;
import com.baselogic.domain.Item;
import com.baselogic.domain.Order;
import com.baselogic.service.ExampleServiceInitializingBeanImpl;

/**
 * Java Configuration
 *
 * <p>Spring Certification objective: 1.5</p>
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
@Configuration
@PropertySource("app-2.properties")
public class JavaConfiguration {
	
	@Value("static JavaConfiguration message")
	private String staticPropertyMessage;

	//@Value("${property.message.placeholder}")
	@Value("${foo}")
	private String propertyMessage;

	/**
	 * referenced by getBean("message")
	 * 
	 * @return
	 */
	@Bean
	public String message() {
		return staticPropertyMessage + ": " + propertyMessage;
	}


	/**
	 * referenced by getBean("javaConfigCustomer")
	 * 
	 * @return
	 */
	@Bean
	public Customer javaConfigCustomer() {
		
		Customer customer = new Customer();
		
		customer.setOrder(javaConfigOrder());

		return customer;
	}

	/**
	 * referenced by getBean("javaConfigOrder")
	 * 
	 * @return
	 */
	@Bean
	@SuppressWarnings("serial")
	public Order javaConfigOrder() {
		
		Order order = new Order();
		order.setItems(
				new LinkedList<Item>(){{
					add(item()); //#1
					add(item()); //#2
					add(item()); //#3
					add(item()); //#4 items
				}}
		);
		
		return order;
	}

	/**
	 * When called from a Singleton, and NOT Proxied, this
	 * prototype Object is also a Singleton.
	 * 
	 * referenced by getBean("item")
	 * 
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public Item item() {
		
		Item item = new Item();
		item.setId(new Random(1234567890L).nextLong());
		item.setPrice(new Random(1234567890L).nextDouble());
		item.setProduct(UUID.randomUUID().toString());
		
		return item;
	}

	@Bean
	@Scope("prototype")
	public Item scopedItem() {
		
		Item item = new Item();
		item.setId(new Random(1234567890L).nextLong());
		item.setPrice(new Random(1234567890L).nextDouble());
		item.setProduct(UUID.randomUUID().toString());
		
		return item;
	}
	
	@Bean
	public ExampleServiceInitializingBeanImpl exampleServiceInitializingBeanImplJavaConfig(){
		
		ExampleServiceInitializingBeanImpl exampleServiceInitializingBeanImplJavaConfig 
			= new ExampleServiceInitializingBeanImpl();
		
		exampleServiceInitializingBeanImplJavaConfig.setMessage("exampleServiceInitializingBeanImplJavaConfig");
		
		return exampleServiceInitializingBeanImplJavaConfig;
	}

}

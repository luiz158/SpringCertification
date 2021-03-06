package com.baselogic.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.baselogic.domain.Item;
import com.baselogic.domain.Order;

/**
 * ComponentFactory
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
public class ComponentFactory implements FactoryBean<Order> {
	
	private Order order = null;

	private List<Item> items;

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public Order getObject() throws Exception {
		
		if (!isSingleton() || order == null) {
			order = new Order();
		}

		order.setItems(items);
		return order;
	}

	@Override
	public Class<? extends Order> getObjectType() {
		return Order.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}

package com.baselogic.dao;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.baselogic.domain.Order;

@Repository
public class OrderHibernateDAOImpl implements OrderDAO {
	
	public Order placeOrder(Order order){
		Long id = new Random(1234567890L).nextLong();
		order.setId(id);
		return order;
	}

}

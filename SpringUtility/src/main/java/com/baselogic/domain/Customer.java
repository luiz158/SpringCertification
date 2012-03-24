package com.baselogic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Customer {

    private String firstName = null;
    private String lastName = null;
    private  Order order = null;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}    
}
package org.bpmnwithactiviti.entity;

import java.io.Serializable;

public class Order implements Serializable	{

	private static final long serialVersionUID = 1L;
	private final String type;

	public Order(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}

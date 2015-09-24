package io.pivotal.cc.app;



import io.pivotal.cc.common.Order;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class OrderMessageHandler {
	

	
	private static Logger logger = Logger.getLogger(OrderMessageHandler.class);
	public OrderMessageHandler(){
		
	}
	public void handleMessage(String text) {
		System.out.println("Received (text): " + text);
	}
	/*
    public void handleMessage(Map map) {
    	System.out.println("Received (map): " + map);
    }
	*/
    public void handleMessage(byte[] bytes) {
    	Order order = Order.fromBytes(bytes);
    	logger.info(" Received Order: " + order);
    	OrderController.registerOrder(order);   	
    }
    
    public void handleMessage(Serializable obj) {
    	System.out.println("Received (serializable): " + obj.toString());    	
    }
}
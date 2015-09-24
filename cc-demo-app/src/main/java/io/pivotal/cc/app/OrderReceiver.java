package io.pivotal.cc.app;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;

public class OrderReceiver {
	
	public OrderReceiver() {
		// TODO Auto-generated constructor stub
	}
	
	public void receiveMessage(String message){
		ApplicationContext ctx = CcDemoAppApplication.getApplicationContext();
		AmqpTemplate amqpTemplate = ctx.getBean(AmqpTemplate.class);
		System.out.println("Received: " + amqpTemplate.receiveAndConvert());
	}

}

/*
package io.pivotal.cc.cacheserver;


import io.pivotal.cc.common.Constants;
import io.pivotal.cc.common.Order;
import io.pivotal.cc.common.OrderRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.stereotype.Component;

//import com.gemstone.gemfire.cache.execute.FunctionContext;

@Component
public class OrderAggregator {
	private static Logger logger = Logger.getLogger(OrderAggregator.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@GemfireFunction
	public Map<String, Double> getSalesForAllStates(){
		Map<String, Double> map = new HashMap<String, Double>();
		
		for(int ii = 0; ii < Constants.states.length; ii++){
			String state = Constants.states[ii];
			logger.info("Getting orders for state: " + state);
			if(orderRepository == null){
				logger.error("OrderRepository is null!");
			}
			Iterable<Order> ordersForState = orderRepository.findByState(state);
			if(ordersForState == null){
				logger.warn("No orders found for state: " + state);
				continue;
			}
			Iterator<Order> it = ordersForState.iterator();
			Double totalSalesInState = 0.0;
			while(it.hasNext()){
				Order o = it.next();
				totalSalesInState = totalSalesInState + o.getAmount();
			}
			map.put(state, totalSalesInState);
		}
		Set<Entry<String, Double>> set = map.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Double> entry = it.next();
			logger.info("Sum of orders for state: " + entry.getKey() + " value: " + entry.getValue());
		}
		//functionContext.getResultSender().lastResult(map);
		return map;
	}

	public OrderAggregator() {
		// TODO Auto-generated constructor stub
	}

}

*/
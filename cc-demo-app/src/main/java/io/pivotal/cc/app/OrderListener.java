package io.pivotal.cc.app;

import io.pivotal.cc.common.Order;

import com.gemstone.gemfire.cache.util.CacheListenerAdapter;

public class OrderListener  extends CacheListenerAdapter<String, Order>{

	public OrderListener() {
	}

}

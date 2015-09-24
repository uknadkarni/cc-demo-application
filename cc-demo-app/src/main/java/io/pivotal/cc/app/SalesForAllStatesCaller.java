package io.pivotal.cc.app;

import java.util.Map;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

@OnRegion(region = "Orders", id="salesForAllStatesCaller")
public interface SalesForAllStatesCaller {

	@FunctionId("getSalesForAllStates")
	public Map<String, Double> getSalesForAllStates();

}

package io.pivotal.cc.app;

import io.pivotal.cc.common.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.execute.Execution;
/*
@OnRegion(region="Orders", id="salesByStateCaller")
public interface SalesByStateCaller {

	@FunctionId("sumSales")
	public Map<String, Double> sumSales();
}
*/
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;

@Component
public class SalesByStateCaller {
	
	@Resource(name="Orders")
	private Region<String, Order> region;

	private static Logger logger = Logger.getLogger(SalesByStateCaller.class);

	public ArrayList computeSum() {
		
		Execution functionExecution = FunctionService.onRegion(region);
		
		ResultCollector<?,?> results = functionExecution.execute("SalesByStateExecutor");
		
		ArrayList<Object> list = (ArrayList<Object>)results.getResult();
		Iterator it = list.iterator();
		while(it.hasNext()){
			logger.info("Entry: " + it.next().toString());
		}
		/*
		Set<Entry<String, Double>> set = map.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Double> entry = it.next();
			logger.info("Sum of orders for state: " + entry.getKey() + " value: " + entry.getValue());
		}
		*/
		return list;
		
	}
	
}
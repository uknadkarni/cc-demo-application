package io.pivotal.cc.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends CrudRepository<Order, String> {

    Order findByTransactionId(String name);
    
    Iterable<Order> findByCreditCardType(String creditCardType);
    
    Iterable<Order> findByZip(int zip);

    Iterable<Order> findByState(String state);

//    Iterable<Order> findByAgeGreaterThanAndAgeLessThan(int age1, int age2);
    
    @SuppressWarnings("unchecked")
	Order save(Order order);
}

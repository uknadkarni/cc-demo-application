package io.pivotal.cc.app;

import io.pivotal.cc.common.OrderRepository;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctionExecutions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.client.ClientCache;

@Configuration
@EnableGemfireRepositories
@EnableGemfireFunctionExecutions(basePackages="io.pivotal.cc.app")
@ImportResource("client-cache.xml")
@ComponentScan(basePackages={"io.pivotal.cc.app", "io.pivotal.cacheserver","io.pivotal.cc.common"})
@SpringBootApplication
public class CcDemoAppApplication {
	static ApplicationContext ctx = null;
	
	// private static Logger logger = Logger.getLogger(CcConfiguration.class);
	// final static String routingKey = "routing-key";
	final static String queueName = "myQueue";
	final static String exchangeName = "myExchange";

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private OrderRepository orderRepository;
	
	 @Autowired
	ClientCache cache;

	// private MessageListenerAdapter listenerAdapter;

	@Bean
	public ConnectionFactory connectionFactory() {
		// Read the host, port, username and password from a config file
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				"localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		// The routing key is set to the name of the queue by the broker for the
		// default exchange.
		template.setRoutingKey(queueName);
		// Where we will synchronously receive messages from
		template.setQueue(queueName);
		return template;
	}

	@Bean
	// Every queue is bound to the default direct exchange
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(new MessageListenerAdapter(
				new OrderMessageHandler()));
		return container;
	}
	

	public static ApplicationContext getApplicationContext(){
		if(ctx == null){
			ctx = SpringApplication.run(CcDemoAppApplication.class);
		}
		return ctx;
	}
    public static void main(String[] args) {
        ctx = SpringApplication.run(CcDemoAppApplication.class, args);
    }
}

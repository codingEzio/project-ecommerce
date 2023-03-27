package com.elliot.mall.portal.config;

import com.elliot.mall.portal.domain.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for configuring the RabbitMQ (message queue) system for handling order cancellations.
 * By creating direct exchanges, queues and bindings, it ensures that messages are properly routed and received by the correct queue.
 *
 * @see ExchangeBuilder
 * @see QueueBuilder
 * @see BindingBuilder
 */
@Configuration
public class RabbitMqConfig {

	/**
	 * Defines the direct exchange for the actual order cancellation queue.
	 *
	 * <p>This exchange routes messages directly to the appropriate queue, ensuring that messages are properly received and processed.
	 * In real-world terms, this would be like an express delivery that goes directly from the sender to the recipient without any intermediate stops.</p>
	 */
	@Bean
	DirectExchange orderDirect() {
		return ExchangeBuilder
				.directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
				.durable(true)
				.build();
	}

	/**
	 * Defines the direct exchange for the order cancellation TTL (time-to-live) queue.
	 *
	 * <p>This exchange delays messages by a specific amount of time before routing them, allowing for flexible delivery scheduling.
	 * In real-world terms, this would be like a letter that is mailed today, but only delivers to its recipient a few days from now.</p>
	 */
	@Bean
	DirectExchange orderTtlDirect() {
		return ExchangeBuilder
				.directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
				.durable(true)
				.build();
	}

	/**
	 * Defines the actual order cancellation queue.
	 *
	 * <p>This queue receives messages directly from the order cancellation exchange, which are processed and cancelled as appropriate.
	 * In real-world terms, this would be like a customer service center that exclusively handles cancellation requests.</p>
	 */
	@Bean
	public Queue orderQueue() {
		return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
	}

	/**
	 * Defines the order cancellation TTL (time-to-live) queue.
	 *
	 * <p>This queue receives delayed messages from the order cancellation TTL exchange, which are then processed and cancelled after the designated delay.
	 * In real-world terms, this would be like a cancellation center that processes requests based on a scheduled delivery date.</p>
	 */
	@Bean
	public Queue orderTtlQueue() {
		return QueueBuilder
				.durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
				.withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
				.withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
				.build();
	}

	/**
	 * Binds the actual order cancellation queue to the order cancellation direct exchange.
	 *
	 * <p>This binding ensures that messages are properly routed from the exchange to the queue based on the appropriate routing key.
	 * In real-world terms, this would be like establishing a direct line of communication between the customer service center and the appropriate department.</p>
	 */
	@Bean
	Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
		return BindingBuilder
				.bind(orderQueue)
				.to(orderDirect)
				.with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
	}

	/**
	 * Binds the order cancellation TTL queue to the order cancellation TTL direct exchange.
	 *
	 * <p>This binding ensures that messages are properly routed from the TTL exchange to the TTL queue with the appropriate delay, based on the routing key.
	 * In real-world terms, this would be like scheduling a delayed delivery based on the customer's requested date.</p>
	 */
	@Bean
	Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue) {
		return BindingBuilder
				.bind(orderTtlQueue)
				.to(orderTtlDirect)
				.with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
	}

}

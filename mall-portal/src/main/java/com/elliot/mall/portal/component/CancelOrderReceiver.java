package com.elliot.mall.portal.component;

import com.elliot.mall.portal.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);
	@Autowired
	private OmsPortalOrderService portalOrderService;

	@RabbitHandler
	public void handle(Long orderId) {
		portalOrderService.cancelOrder(orderId);

		LOGGER.info("process orderId:{}", orderId);
	}
}

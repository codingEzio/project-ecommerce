package com.elliot.mall.portal.component;

import com.elliot.mall.portal.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutCancelTask {
	private final Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);
	@Autowired
	private OmsPortalOrderService portalOrderService;

	/**
	 * 每10分钟扫描一次，扫描超时未支付订单，进行取消操作
	 */
	@Scheduled(cron = "0 0/10 * ? * ?")
	private void cancelTimeOutOrder() {
		Integer count = portalOrderService.cancelTimeOutOrder();
		LOGGER.info("取消订单，并根据sku编号释放锁定库存，取消订单数量：{}", count);
	}
}

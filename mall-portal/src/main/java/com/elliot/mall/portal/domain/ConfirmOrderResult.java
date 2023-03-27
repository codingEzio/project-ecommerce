package com.elliot.mall.portal.domain;

import java.math.BigDecimal;
import java.util.List;

import com.elliot.mall.model.UmsIntegrationConsumeSetting;
import com.elliot.mall.model.UmsMemberReceiveAddress;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmOrderResult {
	@ApiModelProperty("包含优惠信息的购物车信息")
	private List<CartPromotionItem> cartPromotionItemList;
	@ApiModelProperty("用户收货地址列表")
	private List<UmsMemberReceiveAddress> memberReceiveAddressList;
	@ApiModelProperty("用户可用优惠券列表")
	private List<SmsCouponHistoryDetail> couponHistoryDetailList;
	@ApiModelProperty("积分使用规则")
	private UmsIntegrationConsumeSetting integrationConsumeSetting;
	@ApiModelProperty("会员持有的积分")
	private Integer memberIntegration;
	@ApiModelProperty("计算的金额")
	private CalcAmount calcAmount;

	@Getter
	@Setter
	public static class CalcAmount {
		@ApiModelProperty("订单商品总金额")
		private BigDecimal totalAmount;
		@ApiModelProperty("运费")
		private BigDecimal freightAmount;
		@ApiModelProperty("活动优惠")
		private BigDecimal promotionAmount;
		@ApiModelProperty("应付金额")
		private BigDecimal payAmount;
	}
}

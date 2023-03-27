package com.elliot.mall.dto;

import java.util.List;

import com.elliot.mall.model.OmsOrder;
import com.elliot.mall.model.OmsOrderItem;
import com.elliot.mall.model.OmsOrderOperateHistory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class OmsOrderDetail extends OmsOrder {
	@Getter
	@Setter
	@ApiModelProperty("订单商品列表")
	private List<OmsOrderItem> orderItemList;
	@Getter
	@Setter
	@ApiModelProperty("订单操作记录列表")
	private List<OmsOrderOperateHistory> historyList;
}

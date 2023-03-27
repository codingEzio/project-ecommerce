package com.elliot.mall.portal.domain;

import java.util.List;

import com.elliot.mall.model.OmsOrder;
import com.elliot.mall.model.OmsOrderItem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OmsOrderDetail extends OmsOrder {
	@ApiModelProperty("订单商品列表")
	private List<OmsOrderItem> orderItemList;
}

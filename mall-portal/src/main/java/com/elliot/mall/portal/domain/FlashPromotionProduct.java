package com.elliot.mall.portal.domain;

import java.math.BigDecimal;

import com.elliot.mall.model.PmsProduct;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct {
	@ApiModelProperty("秒杀价格")
	private BigDecimal flashPromotionPrice;
	@ApiModelProperty("用于秒杀到数量")
	private Integer flashPromotionCount;
	@ApiModelProperty("秒杀限购数量")
	private Integer flashPromotionLimit;
}

package com.elliot.mall.dto;

import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.model.SmsFlashPromotionProductRelation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
	@Getter
	@Setter
	@ApiModelProperty("关联商品")
	private PmsProduct product;
}

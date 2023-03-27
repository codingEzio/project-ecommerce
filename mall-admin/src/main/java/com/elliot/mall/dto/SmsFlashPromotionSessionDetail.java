package com.elliot.mall.dto;

import com.elliot.mall.model.SmsFlashPromotionSession;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {
	@Setter
	@Getter
	@ApiModelProperty("商品数量")
	private Long productCount;
}

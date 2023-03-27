package com.elliot.mall.portal.domain;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeFlashPromotion {
	@ApiModelProperty("本场开始时间")
	private Date startTime;
	@ApiModelProperty("本场结束时间")
	private Date endTime;
	@ApiModelProperty("下场开始时间")
	private Date nextStartTime;
	@ApiModelProperty("下场结束时间")
	private Date nextEndTime;
	@ApiModelProperty("属于该秒杀活动的商品")
	private List<FlashPromotionProduct> productList;
}

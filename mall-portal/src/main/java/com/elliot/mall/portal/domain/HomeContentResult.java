package com.elliot.mall.portal.domain;

import java.util.List;

import com.elliot.mall.model.CmsSubject;
import com.elliot.mall.model.PmsBrand;
import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.model.SmsHomeAdvertise;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeContentResult {
	@ApiModelProperty("轮播广告")
	private List<SmsHomeAdvertise> advertiseList;
	@ApiModelProperty("推荐品牌")
	private List<PmsBrand> brandList;
	@ApiModelProperty("当前秒杀场次")
	private HomeFlashPromotion homeFlashPromotion;
	@ApiModelProperty("新品推荐")
	private List<PmsProduct> newProductList;
	@ApiModelProperty("人气推荐")
	private List<PmsProduct> hotProductList;
	@ApiModelProperty("推荐专题")
	private List<CmsSubject> subjectList;
}

package com.elliot.mall.portal.domain;

import java.util.List;

import com.elliot.mall.model.SmsCoupon;
import com.elliot.mall.model.SmsCouponHistory;
import com.elliot.mall.model.SmsCouponProductCategoryRelation;
import com.elliot.mall.model.SmsCouponProductRelation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsCouponHistoryDetail extends SmsCouponHistory {
	@ApiModelProperty("相关优惠券信息")
	private SmsCoupon coupon;
	@ApiModelProperty("优惠券关联商品")
	private List<SmsCouponProductRelation> productRelationList;
	@ApiModelProperty("优惠券关联商品分类")
	private List<SmsCouponProductCategoryRelation> categoryRelationList;
}

package com.elliot.mall.dto;

import java.util.List;

import com.elliot.mall.model.SmsCoupon;
import com.elliot.mall.model.SmsCouponProductCategoryRelation;
import com.elliot.mall.model.SmsCouponProductRelation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class SmsCouponParam extends SmsCoupon {
	@Getter
	@Setter
	@ApiModelProperty("优惠券绑定的商品")
	private List<SmsCouponProductRelation> productRelationList;
	@Getter
	@Setter
	@ApiModelProperty("优惠券绑定的商品分类")
	private List<SmsCouponProductCategoryRelation> productCategoryRelationList;
}

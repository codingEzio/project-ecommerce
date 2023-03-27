package com.elliot.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class PmsProductResult extends PmsProductParam {
	@Getter
	@Setter
	@ApiModelProperty("商品所选分类的父id")
	private Long cateParentId;
}

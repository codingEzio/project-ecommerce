package com.elliot.mall.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttrInfo {
	@ApiModelProperty("商品属性ID")
	private Long attributeId;
	@ApiModelProperty("商品属性分类ID")
	private Long attributeCategoryId;
}

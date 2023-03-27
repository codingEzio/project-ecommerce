package com.elliot.mall.dto;

import java.util.List;

import com.elliot.mall.model.PmsProductAttribute;
import com.elliot.mall.model.PmsProductAttributeCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
	@Getter
	@Setter
	@ApiModelProperty(value = "商品属性列表")
	private List<PmsProductAttribute> productAttributeList;
}

package com.elliot.mall.portal.domain;

import java.util.List;

import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.model.PmsProductAttribute;
import com.elliot.mall.model.PmsSkuStock;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProduct extends PmsProduct {
	@ApiModelProperty("商品属性列表")
	private List<PmsProductAttribute> productAttributeList;
	@ApiModelProperty("商品SKU库存列表")
	private List<PmsSkuStock> skuStockList;
}

package com.elliot.mall.portal.domain;

import java.util.List;

import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.model.PmsProductFullReduction;
import com.elliot.mall.model.PmsProductLadder;
import com.elliot.mall.model.PmsSkuStock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionProduct extends PmsProduct {
	// 商品库存信息
	private List<PmsSkuStock> skuStockList;
	// 商品打折信息
	private List<PmsProductLadder> productLadderList;
	// 商品满减信息
	private List<PmsProductFullReduction> productFullReductionList;
}

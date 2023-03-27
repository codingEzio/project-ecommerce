package com.elliot.mall.service;

import com.elliot.mall.model.PmsSkuStock;

import java.util.List;

public interface PmsSkuStockService {
	List<PmsSkuStock> getList(Long pid, String keyword);

	int update(Long pid, List<PmsSkuStock> skuStockList);
}

package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsSkuStock;

public interface PmsSkuStockDao {
	int insertList(@Param("list") List<PmsSkuStock> skuStockList);

	int replaceList(@Param("list") List<PmsSkuStock> skuStockList);
}

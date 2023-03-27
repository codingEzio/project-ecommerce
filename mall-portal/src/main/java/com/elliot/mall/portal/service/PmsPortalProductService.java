package com.elliot.mall.portal.service;

import java.util.List;

import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.portal.domain.PmsPortalProductDetail;
import com.elliot.mall.portal.domain.PmsProductCategoryNode;

public interface PmsPortalProductService {
	/**
	 * 综合搜索商品
	 */
	List<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,
			Integer sort);

	/**
	 * 以树形结构获取所有商品分类
	 */
	List<PmsProductCategoryNode> categoryTreeList();

	/**
	 * 获取前台商品详情
	 */
	PmsPortalProductDetail detail(Long id);
}

package com.elliot.mall.portal.service;

import java.util.List;

import com.elliot.mall.model.CmsSubject;
import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.model.PmsProductCategory;
import com.elliot.mall.portal.domain.HomeContentResult;

public interface HomeService {

	/**
	 * 获取首页内容
	 */
	HomeContentResult content();

	/**
	 * 首页商品推荐
	 */
	List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum);

	/**
	 * 获取商品分类
	 *
	 * @param parentId 0:获取一级分类；其他：获取指定二级分类
	 */
	List<PmsProductCategory> getProductCateList(Long parentId);

	/**
	 * 根据专题分类分页获取专题
	 *
	 * @param cateId 专题分类id
	 */
	List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);

	/**
	 * 分页获取人气推荐商品
	 */
	List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize);

	/**
	 * 分页获取新品推荐商品
	 */
	List<PmsProduct> newProductList(Integer pageNum, Integer pageSize);
}

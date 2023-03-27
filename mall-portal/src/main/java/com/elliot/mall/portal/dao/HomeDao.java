package com.elliot.mall.portal.dao;

import com.elliot.mall.model.CmsSubject;
import com.elliot.mall.model.PmsBrand;
import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.portal.domain.FlashPromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeDao {

	/**
	 * 获取推荐品牌
	 */
	List<PmsBrand> getRecommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);

	/**
	 * 获取秒杀商品
	 */
	List<FlashPromotionProduct> getFlashProductList(@Param("flashPromotionId") Long flashPromotionId,
			@Param("sessionId") Long sessionId);

	/**
	 * 获取新品推荐
	 */
	List<PmsProduct> getNewProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);

	/**
	 * 获取人气推荐
	 */
	List<PmsProduct> getHotProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);

	/**
	 * 获取推荐专题
	 */
	List<CmsSubject> getRecommendSubjectList(@Param("offset") Integer offset, @Param("limit") Integer limit);
}

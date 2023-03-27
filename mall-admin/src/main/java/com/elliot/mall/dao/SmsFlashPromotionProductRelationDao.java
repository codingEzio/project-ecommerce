package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.dto.SmsFlashPromotionProduct;

public interface SmsFlashPromotionProductRelationDao {
	List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId,
			@Param("flashPromotionSessionId") Long flashPromotionSessionId);
}

package com.elliot.mall.portal.service;

import java.util.List;

import com.elliot.mall.model.OmsCartItem;
import com.elliot.mall.portal.domain.CartPromotionItem;

public interface OmsPromotionService {
	/**
	 * 计算购物车中的促销活动信息
	 *
	 * @param cartItemList 购物车
	 */
	List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}

package com.elliot.mall.service;

import com.elliot.mall.model.SmsCouponHistory;

import java.util.List;

public interface SmsCouponHistoryService {
	List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}

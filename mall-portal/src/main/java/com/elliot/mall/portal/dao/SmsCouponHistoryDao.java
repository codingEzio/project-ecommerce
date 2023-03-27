package com.elliot.mall.portal.dao;

import com.elliot.mall.model.SmsCoupon;
import com.elliot.mall.portal.domain.SmsCouponHistoryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsCouponHistoryDao {
	/**
	 * 获取优惠券历史详情
	 */
	List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);

	/**
	 * 获取指定会员优惠券列表
	 */
	List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus") Integer useStatus);
}

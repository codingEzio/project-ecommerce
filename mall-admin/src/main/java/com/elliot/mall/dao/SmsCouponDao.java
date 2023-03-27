package com.elliot.mall.dao;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.dto.SmsCouponParam;

public interface SmsCouponDao {
	SmsCouponParam getItem(@Param("id") Long id);
}

package com.elliot.mall.dao;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.dto.PmsProductResult;

public interface PmsProductDao {
	PmsProductResult getUpdateInfo(@Param("id") Long id);
}

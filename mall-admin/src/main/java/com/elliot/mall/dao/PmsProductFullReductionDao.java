package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsProductFullReduction;

public interface PmsProductFullReductionDao {
	int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}

package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsProductLadder;

public interface PmsProductLadderDao {
	int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}

package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsMemberPrice;

public interface PmsMemberPriceDao {
	int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}

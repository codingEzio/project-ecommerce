package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsProductAttributeValue;

public interface PmsProductAttributeValueDao {
	int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}

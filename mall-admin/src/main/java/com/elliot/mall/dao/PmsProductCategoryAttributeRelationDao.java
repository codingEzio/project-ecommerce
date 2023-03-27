package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsProductCategoryAttributeRelation;

public interface PmsProductCategoryAttributeRelationDao {
	int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> productCategoryAttributeRelationList);
}

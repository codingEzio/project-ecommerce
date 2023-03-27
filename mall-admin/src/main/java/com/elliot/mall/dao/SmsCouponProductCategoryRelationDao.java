package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.SmsCouponProductCategoryRelation;

public interface SmsCouponProductCategoryRelationDao {
	int insertList(@Param("list") List<SmsCouponProductCategoryRelation> productCategoryRelationList);
}

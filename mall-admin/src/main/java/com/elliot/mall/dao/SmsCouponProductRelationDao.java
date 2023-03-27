package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.SmsCouponProductRelation;

public interface SmsCouponProductRelationDao {
	int insertList(@Param("list") List<SmsCouponProductRelation> productRelationList);
}

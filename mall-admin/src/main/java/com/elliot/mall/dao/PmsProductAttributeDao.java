package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.dto.ProductAttrInfo;

public interface PmsProductAttributeDao {
	List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}

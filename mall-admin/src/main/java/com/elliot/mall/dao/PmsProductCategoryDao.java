package com.elliot.mall.dao;

import java.util.List;

import com.elliot.mall.dto.PmsProductCategoryWithChildrenItem;

public interface PmsProductCategoryDao {
	List<PmsProductCategoryWithChildrenItem> listWithChildren();
}

package com.elliot.mall.dao;

import java.util.List;

import com.elliot.mall.dto.PmsProductAttributeCategoryItem;

public interface PmsProductAttributeCategoryDao {
	List<PmsProductAttributeCategoryItem> getListWithAttr();
}

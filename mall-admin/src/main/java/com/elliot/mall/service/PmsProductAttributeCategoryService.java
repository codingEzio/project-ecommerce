package com.elliot.mall.service;

import com.elliot.mall.dto.PmsProductAttributeCategoryItem;
import com.elliot.mall.model.PmsProductAttributeCategory;

import java.util.List;

public interface PmsProductAttributeCategoryService {
	int create(String name);

	int update(Long id, String name);

	int delete(Long id);

	PmsProductAttributeCategory getItem(Long id);

	List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

	List<PmsProductAttributeCategoryItem> getListWithAttr();
}

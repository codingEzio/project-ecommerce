package com.elliot.mall.service;

import com.elliot.mall.dto.PmsProductCategoryParam;
import com.elliot.mall.dto.PmsProductCategoryWithChildrenItem;
import com.elliot.mall.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PmsProductCategoryService {
	@Transactional
	int create(PmsProductCategoryParam pmsProductCategoryParam);

	@Transactional
	int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);

	List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

	int delete(Long id);

	PmsProductCategory getItem(Long id);

	int updateNavStatus(List<Long> ids, Integer navStatus);

	int updateShowStatus(List<Long> ids, Integer showStatus);

	List<PmsProductCategoryWithChildrenItem> listWithChildren();
}

package com.elliot.mall.search.service;

import com.elliot.mall.search.domain.EsProduct;
import com.elliot.mall.search.domain.EsProductRelatedInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {
	int importAll();

	void delete(Long id);

	EsProduct create(Long id);

	void delete(List<Long> ids);

	Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

	Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

	Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize);

	EsProductRelatedInfo searchRelatedInfo(String keyword);
}

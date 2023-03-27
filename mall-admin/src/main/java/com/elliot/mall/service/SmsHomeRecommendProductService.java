package com.elliot.mall.service;

import com.elliot.mall.model.SmsHomeRecommendProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SmsHomeRecommendProductService {
	@Transactional
	int create(List<SmsHomeRecommendProduct> homeRecommendProductList);

	int updateSort(Long id, Integer sort);

	int delete(List<Long> ids);

	int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

	List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);
}

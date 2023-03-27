package com.elliot.mall.service;

import com.elliot.mall.dto.PmsProductParam;
import com.elliot.mall.dto.PmsProductQueryParam;
import com.elliot.mall.dto.PmsProductResult;
import com.elliot.mall.model.PmsProduct;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PmsProductService {
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	int create(PmsProductParam productParam);

	PmsProductResult getUpdateInfo(Long id);

	@Transactional
	int update(Long id, PmsProductParam productParam);

	List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

	@Transactional
	int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

	int updatePublishStatus(List<Long> ids, Integer publishStatus);

	int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

	int updateNewStatus(List<Long> ids, Integer newStatus);

	int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

	List<PmsProduct> list(String keyword);
}

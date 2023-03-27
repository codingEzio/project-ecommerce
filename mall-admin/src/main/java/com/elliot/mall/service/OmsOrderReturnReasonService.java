package com.elliot.mall.service;

import com.elliot.mall.model.OmsOrderReturnReason;

import java.util.List;

public interface OmsOrderReturnReasonService {
	/**
	 * Add return reason.
	 */
	int create(OmsOrderReturnReason returnReason);

	/**
	 * Update return reason.
	 */
	int update(Long id, OmsOrderReturnReason returnReason);

	/**
	 * Batch delete return reasons.
	 */
	int delete(List<Long> ids);

	/**
	 * Get return reasons with pagination.
	 */
	List<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

	/**
	 * Batch update status of return reasons.
	 */
	int updateStatus(List<Long> ids, Integer status);

	/**
	 * Get details of a single return reason.
	 */
	OmsOrderReturnReason getItem(Long id);
}

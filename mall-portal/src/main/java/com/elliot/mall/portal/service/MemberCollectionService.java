package com.elliot.mall.portal.service;

import org.springframework.data.domain.Page;

import com.elliot.mall.portal.domain.MemberProductCollection;

public interface MemberCollectionService {
	/**
	 * 添加收藏
	 */
	int add(MemberProductCollection productCollection);

	/**
	 * 删除收藏
	 */
	int delete(Long productId);

	/**
	 * 分页查询收藏
	 */
	Page<MemberProductCollection> list(Integer pageNum, Integer pageSize);

	/**
	 * 查看收藏详情
	 */
	MemberProductCollection detail(Long productId);

	/**
	 * 清空收藏
	 */
	void clear();
}

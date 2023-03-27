package com.elliot.mall.portal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.elliot.mall.portal.domain.MemberReadHistory;

public interface MemberReadHistoryService {
	/**
	 * 生成浏览记录
	 */
	int create(MemberReadHistory memberReadHistory);

	/**
	 * 批量删除浏览记录
	 */
	int delete(List<String> ids);

	/**
	 * 分页获取用户浏览历史记录
	 */
	Page<MemberReadHistory> list(Integer pageNum, Integer pageSize);

	/**
	 * 清空浏览记录
	 */
	void clear();
}

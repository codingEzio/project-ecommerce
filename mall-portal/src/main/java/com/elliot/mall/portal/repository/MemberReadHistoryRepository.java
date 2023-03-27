package com.elliot.mall.portal.repository;

import com.elliot.mall.portal.domain.MemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
	/**
	 * 根据会员ID分页查找记录
	 */
	Page<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId, Pageable pageable);

	/**
	 * 根据会员ID删除记录
	 */
	void deleteAllByMemberId(Long memberId);
}

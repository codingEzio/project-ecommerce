package com.elliot.mall.portal.service;

import com.elliot.mall.model.UmsMember;

public interface UmsMemberCacheService {
	/**
	 * 删除会员用户缓存
	 */
	void delMember(Long memberId);

	/**
	 * 获取会员用户缓存
	 */
	UmsMember getMember(String username);

	/**
	 * 设置会员用户缓存
	 */
	void setMember(UmsMember member);

	/**
	 * 设置验证码
	 */
	void setAuthCode(String telephone, String authCode);

	/**
	 * 获取验证码
	 */
	String getAuthCode(String telephone);
}

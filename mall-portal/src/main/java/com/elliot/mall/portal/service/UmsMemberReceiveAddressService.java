package com.elliot.mall.portal.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.elliot.mall.model.UmsMemberReceiveAddress;

public interface UmsMemberReceiveAddressService {
	/**
	 * 添加收货地址
	 */
	int add(UmsMemberReceiveAddress address);

	/**
	 * 删除收货地址
	 *
	 * @param id 地址表的id
	 */
	int delete(Long id);

	/**
	 * 修改收货地址
	 *
	 * @param id      地址表的id
	 * @param address 修改的收货地址信息
	 */
	@Transactional
	int update(Long id, UmsMemberReceiveAddress address);

	/**
	 * 返回当前用户的收货地址
	 */
	List<UmsMemberReceiveAddress> list();

	/**
	 * 获取地址详情
	 *
	 * @param id 地址id
	 */
	UmsMemberReceiveAddress getItem(Long id);
}

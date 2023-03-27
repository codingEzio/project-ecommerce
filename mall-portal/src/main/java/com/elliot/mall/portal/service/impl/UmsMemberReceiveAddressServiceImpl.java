package com.elliot.mall.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.elliot.mall.mapper.UmsMemberReceiveAddressMapper;
import com.elliot.mall.model.UmsMember;
import com.elliot.mall.model.UmsMemberReceiveAddress;
import com.elliot.mall.model.UmsMemberReceiveAddressExample;
import com.elliot.mall.portal.service.UmsMemberReceiveAddressService;
import com.elliot.mall.portal.service.UmsMemberService;

@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
	@Autowired
	private UmsMemberService memberService;
	@Autowired
	private UmsMemberReceiveAddressMapper addressMapper;

	@Override
	public int add(UmsMemberReceiveAddress address) {
		UmsMember currentMember = memberService.getCurrentMember();
		address.setMemberId(currentMember.getId());
		return addressMapper.insert(address);
	}

	@Override
	public int delete(Long id) {
		UmsMember currentMember = memberService.getCurrentMember();
		UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
		example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
		return addressMapper.deleteByExample(example);
	}

	@Override
	public int update(Long id, UmsMemberReceiveAddress address) {
		address.setId(null);
		UmsMember currentMember = memberService.getCurrentMember();
		UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
		example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
		if (address.getDefaultStatus() == 1) {
			// 先将原来的默认地址去除
			UmsMemberReceiveAddress record = new UmsMemberReceiveAddress();
			record.setDefaultStatus(0);
			UmsMemberReceiveAddressExample updateExample = new UmsMemberReceiveAddressExample();
			updateExample.createCriteria()
					.andMemberIdEqualTo(currentMember.getId())
					.andDefaultStatusEqualTo(1);
			addressMapper.updateByExampleSelective(record, updateExample);
		}
		return addressMapper.updateByExampleSelective(address, example);
	}

	@Override
	public List<UmsMemberReceiveAddress> list() {
		UmsMember currentMember = memberService.getCurrentMember();
		UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
		example.createCriteria().andMemberIdEqualTo(currentMember.getId());
		return addressMapper.selectByExample(example);
	}

	@Override
	public UmsMemberReceiveAddress getItem(Long id) {
		UmsMember currentMember = memberService.getCurrentMember();
		UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
		example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
		List<UmsMemberReceiveAddress> addressList = addressMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(addressList)) {
			return addressList.get(0);
		}
		return null;
	}
}

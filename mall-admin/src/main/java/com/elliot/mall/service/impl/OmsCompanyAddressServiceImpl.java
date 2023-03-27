package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.OmsCompanyAddressMapper;
import com.elliot.mall.model.OmsCompanyAddress;
import com.elliot.mall.model.OmsCompanyAddressExample;
import com.elliot.mall.service.OmsCompanyAddressService;

@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
	@Autowired
	private OmsCompanyAddressMapper companyAddressMapper;

	@Override
	public List<OmsCompanyAddress> list() {
		return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
	}
}

package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.UmsMemberLevelMapper;
import com.elliot.mall.model.UmsMemberLevel;
import com.elliot.mall.model.UmsMemberLevelExample;
import com.elliot.mall.service.UmsMemberLevelService;

@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
	@Autowired
	private UmsMemberLevelMapper memberLevelMapper;

	@Override
	public List<UmsMemberLevel> list(Integer defaultStatus) {
		UmsMemberLevelExample example = new UmsMemberLevelExample();
		example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
		return memberLevelMapper.selectByExample(example);
	}
}

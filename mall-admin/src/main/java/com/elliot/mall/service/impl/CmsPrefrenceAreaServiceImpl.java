package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.CmsPrefrenceAreaMapper;
import com.elliot.mall.model.CmsPrefrenceArea;
import com.elliot.mall.model.CmsPrefrenceAreaExample;
import com.elliot.mall.service.CmsPrefrenceAreaService;

@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
	@Autowired
	private CmsPrefrenceAreaMapper prefrenceAreaMapper;

	@Override
	public List<CmsPrefrenceArea> listAll() {
		return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
	}
}

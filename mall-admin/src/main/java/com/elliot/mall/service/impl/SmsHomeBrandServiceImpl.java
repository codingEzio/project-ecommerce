package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.SmsHomeBrandMapper;
import com.elliot.mall.model.SmsHomeBrand;
import com.elliot.mall.model.SmsHomeBrandExample;
import com.elliot.mall.service.SmsHomeBrandService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
	@Autowired
	private SmsHomeBrandMapper homeBrandMapper;

	@Override
	public int create(List<SmsHomeBrand> homeBrandList) {
		for (SmsHomeBrand smsHomeBrand : homeBrandList) {
			smsHomeBrand.setRecommendStatus(1);
			smsHomeBrand.setSort(0);
			homeBrandMapper.insert(smsHomeBrand);
		}
		return homeBrandList.size();
	}

	@Override
	public int updateSort(Long id, Integer sort) {
		SmsHomeBrand homeBrand = new SmsHomeBrand();
		homeBrand.setId(id);
		homeBrand.setSort(sort);
		return homeBrandMapper.updateByPrimaryKeySelective(homeBrand);
	}

	@Override
	public int delete(List<Long> ids) {
		SmsHomeBrandExample example = new SmsHomeBrandExample();
		example.createCriteria().andIdIn(ids);
		return homeBrandMapper.deleteByExample(example);
	}

	@Override
	public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
		SmsHomeBrandExample example = new SmsHomeBrandExample();
		example.createCriteria().andIdIn(ids);
		SmsHomeBrand record = new SmsHomeBrand();
		record.setRecommendStatus(recommendStatus);
		return homeBrandMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		SmsHomeBrandExample example = new SmsHomeBrandExample();
		SmsHomeBrandExample.Criteria criteria = example.createCriteria();
		if (!StrUtil.isEmpty(brandName)) {
			criteria.andBrandNameLike("%" + brandName + "%");
		}
		if (recommendStatus != null) {
			criteria.andRecommendStatusEqualTo(recommendStatus);
		}
		example.setOrderByClause("sort desc");
		return homeBrandMapper.selectByExample(example);
	}
}

package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.SmsHomeRecommendSubjectMapper;
import com.elliot.mall.model.SmsHomeRecommendSubject;
import com.elliot.mall.model.SmsHomeRecommendSubjectExample;
import com.elliot.mall.service.SmsHomeRecommendSubjectService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class SmsHomeRecommendSubjectServiceImpl implements SmsHomeRecommendSubjectService {
	@Autowired
	private SmsHomeRecommendSubjectMapper recommendProductMapper;

	@Override
	public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
		for (SmsHomeRecommendSubject recommendProduct : recommendSubjectList) {
			recommendProduct.setRecommendStatus(1);
			recommendProduct.setSort(0);
			recommendProductMapper.insert(recommendProduct);
		}
		return recommendSubjectList.size();
	}

	@Override
	public int updateSort(Long id, Integer sort) {
		SmsHomeRecommendSubject recommendProduct = new SmsHomeRecommendSubject();
		recommendProduct.setId(id);
		recommendProduct.setSort(sort);
		return recommendProductMapper.updateByPrimaryKeySelective(recommendProduct);
	}

	@Override
	public int delete(List<Long> ids) {
		SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
		example.createCriteria().andIdIn(ids);
		return recommendProductMapper.deleteByExample(example);
	}

	@Override
	public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
		SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
		example.createCriteria().andIdIn(ids);
		SmsHomeRecommendSubject record = new SmsHomeRecommendSubject();
		record.setRecommendStatus(recommendStatus);
		return recommendProductMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize,
			Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		SmsHomeRecommendSubjectExample example = new SmsHomeRecommendSubjectExample();
		SmsHomeRecommendSubjectExample.Criteria criteria = example.createCriteria();
		if (!StrUtil.isEmpty(subjectName)) {
			criteria.andSubjectNameLike("%" + subjectName + "%");
		}
		if (recommendStatus != null) {
			criteria.andRecommendStatusEqualTo(recommendStatus);
		}
		example.setOrderByClause("sort desc");
		return recommendProductMapper.selectByExample(example);
	}
}

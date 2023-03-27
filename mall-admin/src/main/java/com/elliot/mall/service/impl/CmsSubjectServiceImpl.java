package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.CmsSubjectMapper;
import com.elliot.mall.model.CmsSubject;
import com.elliot.mall.model.CmsSubjectExample;
import com.elliot.mall.service.CmsSubjectService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {
	@Autowired
	private CmsSubjectMapper subjectMapper;

	@Override
	public List<CmsSubject> listAll() {
		return subjectMapper.selectByExample(new CmsSubjectExample());
	}

	@Override
	public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		CmsSubjectExample example = new CmsSubjectExample();
		CmsSubjectExample.Criteria criteria = example.createCriteria();
		if (!StrUtil.isEmpty(keyword)) {
			criteria.andTitleLike("%" + keyword + "%");
		}
		return subjectMapper.selectByExample(example);
	}
}

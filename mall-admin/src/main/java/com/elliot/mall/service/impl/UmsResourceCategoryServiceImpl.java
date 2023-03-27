package com.elliot.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.UmsResourceCategoryMapper;
import com.elliot.mall.model.UmsResourceCategory;
import com.elliot.mall.model.UmsResourceCategoryExample;
import com.elliot.mall.service.UmsResourceCategoryService;

@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
	@Autowired
	private UmsResourceCategoryMapper resourceCategoryMapper;

	@Override
	public List<UmsResourceCategory> listAll() {
		UmsResourceCategoryExample example = new UmsResourceCategoryExample();
		example.setOrderByClause("sort desc");
		return resourceCategoryMapper.selectByExample(example);
	}

	@Override
	public int create(UmsResourceCategory umsResourceCategory) {
		umsResourceCategory.setCreateTime(new Date());
		return resourceCategoryMapper.insert(umsResourceCategory);
	}

	@Override
	public int update(Long id, UmsResourceCategory umsResourceCategory) {
		umsResourceCategory.setId(id);
		return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
	}

	@Override
	public int delete(Long id) {
		return resourceCategoryMapper.deleteByPrimaryKey(id);
	}
}

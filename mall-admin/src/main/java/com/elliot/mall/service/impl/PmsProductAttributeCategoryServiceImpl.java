package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.dao.PmsProductAttributeCategoryDao;
import com.elliot.mall.dto.PmsProductAttributeCategoryItem;
import com.elliot.mall.mapper.PmsProductAttributeCategoryMapper;
import com.elliot.mall.model.PmsProductAttributeCategory;
import com.elliot.mall.model.PmsProductAttributeCategoryExample;
import com.elliot.mall.service.PmsProductAttributeCategoryService;
import com.github.pagehelper.PageHelper;

@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
	@Autowired
	private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
	@Autowired
	private PmsProductAttributeCategoryDao productAttributeCategoryDao;

	@Override
	public int create(String name) {
		PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
		productAttributeCategory.setName(name);
		return productAttributeCategoryMapper.insertSelective(productAttributeCategory);
	}

	@Override
	public int update(Long id, String name) {
		PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
		productAttributeCategory.setName(name);
		productAttributeCategory.setId(id);
		return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
	}

	@Override
	public int delete(Long id) {
		return productAttributeCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PmsProductAttributeCategory getItem(Long id) {
		return productAttributeCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
	}

	@Override
	public List<PmsProductAttributeCategoryItem> getListWithAttr() {
		return productAttributeCategoryDao.getListWithAttr();
	}
}

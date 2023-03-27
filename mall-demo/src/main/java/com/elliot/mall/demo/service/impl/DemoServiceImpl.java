package com.elliot.mall.demo.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.demo.dto.PmsBrandDto;
import com.elliot.mall.demo.service.DemoService;
import com.elliot.mall.mapper.PmsBrandMapper;
import com.elliot.mall.model.PmsBrand;
import com.elliot.mall.model.PmsBrandExample;
import com.github.pagehelper.PageHelper;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private PmsBrandMapper brandMapper;

	@Override
	public List<PmsBrand> listAllBrand() {
		return brandMapper.selectByExample(new PmsBrandExample());
	}

	@Override
	public int createBrand(PmsBrandDto pmsBrandDto) {
		PmsBrand pmsBrand = new PmsBrand();
		BeanUtils.copyProperties(pmsBrandDto, pmsBrand);
		return brandMapper.insertSelective(pmsBrand);
	}

	@Override
	public int updateBrand(Long id, PmsBrandDto pmsBrandDto) {
		PmsBrand pmsBrand = new PmsBrand();
		BeanUtils.copyProperties(pmsBrandDto, pmsBrand);
		pmsBrand.setId(id);
		return brandMapper.updateByPrimaryKeySelective(pmsBrand);
	}

	@Override
	public int deleteBrand(Long id) {
		return brandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<PmsBrand> listBrand(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return brandMapper.selectByExample(new PmsBrandExample());
	}

	@Override
	public PmsBrand getBrand(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}
}

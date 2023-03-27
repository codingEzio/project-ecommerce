package com.elliot.mall.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.common.api.CommonPage;
import com.elliot.mall.mapper.PmsBrandMapper;
import com.elliot.mall.mapper.PmsProductMapper;
import com.elliot.mall.model.PmsBrand;
import com.elliot.mall.model.PmsProduct;
import com.elliot.mall.model.PmsProductExample;
import com.elliot.mall.portal.dao.HomeDao;
import com.elliot.mall.portal.service.PmsPortalBrandService;
import com.github.pagehelper.PageHelper;

@Service
public class PmsPortalBrandServiceImpl implements PmsPortalBrandService {
	@Autowired
	private HomeDao homeDao;
	@Autowired
	private PmsBrandMapper brandMapper;
	@Autowired
	private PmsProductMapper productMapper;

	@Override
	public List<PmsBrand> recommendList(Integer pageNum, Integer pageSize) {
		int offset = (pageNum - 1) * pageSize;
		return homeDao.getRecommendBrandList(offset, pageSize);
	}

	@Override
	public PmsBrand detail(Long brandId) {
		return brandMapper.selectByPrimaryKey(brandId);
	}

	@Override
	public CommonPage<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PmsProductExample example = new PmsProductExample();
		example.createCriteria().andDeleteStatusEqualTo(0)
				.andPublishStatusEqualTo(1)
				.andBrandIdEqualTo(brandId);
		List<PmsProduct> productList = productMapper.selectByExample(example);
		return CommonPage.restPage(productList);
	}
}

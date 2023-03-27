package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.SmsHomeRecommendProductMapper;
import com.elliot.mall.model.SmsHomeRecommendProduct;
import com.elliot.mall.model.SmsHomeRecommendProductExample;
import com.elliot.mall.service.SmsHomeRecommendProductService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class SmsHomeRecommendProductServiceImpl implements SmsHomeRecommendProductService {
	@Autowired
	private SmsHomeRecommendProductMapper recommendProductMapper;

	@Override
	public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
		for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
			recommendProduct.setRecommendStatus(1);
			recommendProduct.setSort(0);
			recommendProductMapper.insert(recommendProduct);
		}
		return homeRecommendProductList.size();
	}

	@Override
	public int updateSort(Long id, Integer sort) {
		SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
		recommendProduct.setId(id);
		recommendProduct.setSort(sort);
		return recommendProductMapper.updateByPrimaryKeySelective(recommendProduct);
	}

	@Override
	public int delete(List<Long> ids) {
		SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
		example.createCriteria().andIdIn(ids);
		return recommendProductMapper.deleteByExample(example);
	}

	@Override
	public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
		SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
		example.createCriteria().andIdIn(ids);
		SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
		record.setRecommendStatus(recommendStatus);
		return recommendProductMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize,
			Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		SmsHomeRecommendProductExample example = new SmsHomeRecommendProductExample();
		SmsHomeRecommendProductExample.Criteria criteria = example.createCriteria();
		if (!StrUtil.isEmpty(productName)) {
			criteria.andProductNameLike("%" + productName + "%");
		}
		if (recommendStatus != null) {
			criteria.andRecommendStatusEqualTo(recommendStatus);
		}
		example.setOrderByClause("sort desc");
		return recommendProductMapper.selectByExample(example);
	}
}

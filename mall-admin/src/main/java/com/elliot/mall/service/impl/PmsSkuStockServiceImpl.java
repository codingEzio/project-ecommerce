package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.dao.PmsSkuStockDao;
import com.elliot.mall.mapper.PmsSkuStockMapper;
import com.elliot.mall.model.PmsSkuStock;
import com.elliot.mall.model.PmsSkuStockExample;
import com.elliot.mall.service.PmsSkuStockService;

import cn.hutool.core.util.StrUtil;

@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
	@Autowired
	private PmsSkuStockMapper skuStockMapper;
	@Autowired
	private PmsSkuStockDao skuStockDao;

	@Override
	public List<PmsSkuStock> getList(Long pid, String keyword) {
		PmsSkuStockExample example = new PmsSkuStockExample();
		PmsSkuStockExample.Criteria criteria = example.createCriteria().andProductIdEqualTo(pid);
		if (!StrUtil.isEmpty(keyword)) {
			criteria.andSkuCodeLike("%" + keyword + "%");
		}
		return skuStockMapper.selectByExample(example);
	}

	@Override
	public int update(Long pid, List<PmsSkuStock> skuStockList) {
		return skuStockDao.replaceList(skuStockList);
	}
}

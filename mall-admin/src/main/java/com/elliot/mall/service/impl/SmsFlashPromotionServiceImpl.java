package com.elliot.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.mapper.SmsFlashPromotionMapper;
import com.elliot.mall.model.SmsFlashPromotion;
import com.elliot.mall.model.SmsFlashPromotionExample;
import com.elliot.mall.service.SmsFlashPromotionService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
	@Autowired
	private SmsFlashPromotionMapper flashPromotionMapper;

	@Override
	public int create(SmsFlashPromotion flashPromotion) {
		flashPromotion.setCreateTime(new Date());
		return flashPromotionMapper.insert(flashPromotion);
	}

	@Override
	public int update(Long id, SmsFlashPromotion flashPromotion) {
		flashPromotion.setId(id);
		return flashPromotionMapper.updateByPrimaryKey(flashPromotion);
	}

	@Override
	public int delete(Long id) {
		return flashPromotionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateStatus(Long id, Integer status) {
		SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
		flashPromotion.setId(id);
		flashPromotion.setStatus(status);
		return flashPromotionMapper.updateByPrimaryKeySelective(flashPromotion);
	}

	@Override
	public SmsFlashPromotion getItem(Long id) {
		return flashPromotionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		SmsFlashPromotionExample example = new SmsFlashPromotionExample();
		if (!StrUtil.isEmpty(keyword)) {
			example.createCriteria().andTitleLike("%" + keyword + "%");
		}
		return flashPromotionMapper.selectByExample(example);
	}
}

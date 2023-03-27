package com.elliot.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.dao.SmsFlashPromotionProductRelationDao;
import com.elliot.mall.dto.SmsFlashPromotionProduct;
import com.elliot.mall.mapper.SmsFlashPromotionProductRelationMapper;
import com.elliot.mall.model.SmsFlashPromotionProductRelation;
import com.elliot.mall.model.SmsFlashPromotionProductRelationExample;
import com.elliot.mall.service.SmsFlashPromotionProductRelationService;
import com.github.pagehelper.PageHelper;

@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
	@Autowired
	private SmsFlashPromotionProductRelationMapper relationMapper;
	@Autowired
	private SmsFlashPromotionProductRelationDao relationDao;

	@Override
	public int create(List<SmsFlashPromotionProductRelation> relationList) {
		for (SmsFlashPromotionProductRelation relation : relationList) {
			relationMapper.insert(relation);
		}
		return relationList.size();
	}

	@Override
	public int update(Long id, SmsFlashPromotionProductRelation relation) {
		relation.setId(id);
		return relationMapper.updateByPrimaryKey(relation);
	}

	@Override
	public int delete(Long id) {
		return relationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SmsFlashPromotionProductRelation getItem(Long id) {
		return relationMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize,
			Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return relationDao.getList(flashPromotionId, flashPromotionSessionId);
	}

	@Override
	public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
		SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
		example.createCriteria()
				.andFlashPromotionIdEqualTo(flashPromotionId)
				.andFlashPromotionSessionIdEqualTo(flashPromotionSessionId);
		return relationMapper.countByExample(example);
	}
}

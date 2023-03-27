package com.elliot.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.dao.OmsOrderReturnApplyDao;
import com.elliot.mall.dto.OmsOrderReturnApplyResult;
import com.elliot.mall.dto.OmsReturnApplyQueryParam;
import com.elliot.mall.dto.OmsUpdateStatusParam;
import com.elliot.mall.mapper.OmsOrderReturnApplyMapper;
import com.elliot.mall.model.OmsOrderReturnApply;
import com.elliot.mall.model.OmsOrderReturnApplyExample;
import com.elliot.mall.service.OmsOrderReturnApplyService;
import com.github.pagehelper.PageHelper;

@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {
	@Autowired
	private OmsOrderReturnApplyDao returnApplyDao;
	@Autowired
	private OmsOrderReturnApplyMapper returnApplyMapper;

	@Override
	public List<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return returnApplyDao.getList(queryParam);
	}

	@Override
	public int delete(List<Long> ids) {
		OmsOrderReturnApplyExample example = new OmsOrderReturnApplyExample();
		example.createCriteria().andIdIn(ids).andStatusEqualTo(3);
		return returnApplyMapper.deleteByExample(example);
	}

	@Override
	public int updateStatus(Long id, OmsUpdateStatusParam statusParam) {
		Integer status = statusParam.getStatus();
		OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
		if (status.equals(1)) {
			// 确认退货
			returnApply.setId(id);
			returnApply.setStatus(1);
			returnApply.setReturnAmount(statusParam.getReturnAmount());
			returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
			returnApply.setHandleTime(new Date());
			returnApply.setHandleMan(statusParam.getHandleMan());
			returnApply.setHandleNote(statusParam.getHandleNote());
		} else if (status.equals(2)) {
			// 完成退货
			returnApply.setId(id);
			returnApply.setStatus(2);
			returnApply.setReceiveTime(new Date());
			returnApply.setReceiveMan(statusParam.getReceiveMan());
			returnApply.setReceiveNote(statusParam.getReceiveNote());
		} else if (status.equals(3)) {
			// 拒绝退货
			returnApply.setId(id);
			returnApply.setStatus(3);
			returnApply.setHandleTime(new Date());
			returnApply.setHandleMan(statusParam.getHandleMan());
			returnApply.setHandleNote(statusParam.getHandleNote());
		} else {
			return 0;
		}
		return returnApplyMapper.updateByPrimaryKeySelective(returnApply);
	}

	@Override
	public OmsOrderReturnApplyResult getItem(Long id) {
		return returnApplyDao.getDetail(id);
	}
}

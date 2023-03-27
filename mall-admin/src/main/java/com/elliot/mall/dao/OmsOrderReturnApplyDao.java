package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.dto.OmsOrderReturnApplyResult;
import com.elliot.mall.dto.OmsReturnApplyQueryParam;
import com.elliot.mall.model.OmsOrderReturnApply;

public interface OmsOrderReturnApplyDao {
	List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

	OmsOrderReturnApplyResult getDetail(@Param("id") Long id);
}

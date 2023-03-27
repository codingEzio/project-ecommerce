package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.OmsOrderOperateHistory;

public interface OmsOrderOperateHistoryDao {
	int insertList(@Param("list") List<OmsOrderOperateHistory> orderOperateHistoryList);
}

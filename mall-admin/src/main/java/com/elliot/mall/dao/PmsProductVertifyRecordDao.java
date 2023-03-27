package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.PmsProductVertifyRecord;

public interface PmsProductVertifyRecordDao {
	int insertList(@Param("list") List<PmsProductVertifyRecord> list);
}

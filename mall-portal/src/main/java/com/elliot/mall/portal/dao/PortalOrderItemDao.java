package com.elliot.mall.portal.dao;

import com.elliot.mall.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalOrderItemDao {
	/**
	 * 批量插入
	 */
	int insertList(@Param("list") List<OmsOrderItem> list);
}

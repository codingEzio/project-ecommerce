package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.dto.OmsOrderDeliveryParam;
import com.elliot.mall.dto.OmsOrderDetail;
import com.elliot.mall.dto.OmsOrderQueryParam;
import com.elliot.mall.model.OmsOrder;

public interface OmsOrderDao {
	List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

	int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

	OmsOrderDetail getDetail(@Param("id") Long id);
}

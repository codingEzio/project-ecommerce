package com.elliot.mall.service;

import com.elliot.mall.model.SmsHomeAdvertise;

import java.util.List;

public interface SmsHomeAdvertiseService {
	int create(SmsHomeAdvertise advertise);

	int delete(List<Long> ids);

	int updateStatus(Long id, Integer status);

	SmsHomeAdvertise getItem(Long id);

	int update(Long id, SmsHomeAdvertise advertise);

	List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}

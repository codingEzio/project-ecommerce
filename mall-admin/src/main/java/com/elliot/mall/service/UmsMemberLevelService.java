package com.elliot.mall.service;

import com.elliot.mall.model.UmsMemberLevel;

import java.util.List;

public interface UmsMemberLevelService {
	List<UmsMemberLevel> list(Integer defaultStatus);
}

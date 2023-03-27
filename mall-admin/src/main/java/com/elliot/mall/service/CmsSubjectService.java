package com.elliot.mall.service;

import com.elliot.mall.model.CmsSubject;

import java.util.List;

public interface CmsSubjectService {
	List<CmsSubject> listAll();

	List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}

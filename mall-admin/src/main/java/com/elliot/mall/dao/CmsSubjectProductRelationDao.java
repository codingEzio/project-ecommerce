package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.CmsSubjectProductRelation;

public interface CmsSubjectProductRelationDao {
	int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}

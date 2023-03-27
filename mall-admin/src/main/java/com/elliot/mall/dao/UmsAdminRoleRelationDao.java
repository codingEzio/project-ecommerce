package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.UmsAdminRoleRelation;
import com.elliot.mall.model.UmsResource;
import com.elliot.mall.model.UmsRole;

public interface UmsAdminRoleRelationDao {
	int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

	List<UmsRole> getRoleList(@Param("adminId") Long adminId);

	List<UmsResource> getResourceList(@Param("adminId") Long adminId);

	List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}

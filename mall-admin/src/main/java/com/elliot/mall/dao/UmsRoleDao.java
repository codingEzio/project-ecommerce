package com.elliot.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elliot.mall.model.UmsMenu;
import com.elliot.mall.model.UmsResource;

public interface UmsRoleDao {
	List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

	List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

	List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}

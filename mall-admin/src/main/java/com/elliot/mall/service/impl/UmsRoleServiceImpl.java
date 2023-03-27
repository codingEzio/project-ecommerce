package com.elliot.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elliot.mall.dao.UmsRoleDao;
import com.elliot.mall.mapper.UmsRoleMapper;
import com.elliot.mall.mapper.UmsRoleMenuRelationMapper;
import com.elliot.mall.mapper.UmsRoleResourceRelationMapper;
import com.elliot.mall.model.UmsMenu;
import com.elliot.mall.model.UmsResource;
import com.elliot.mall.model.UmsRole;
import com.elliot.mall.model.UmsRoleExample;
import com.elliot.mall.model.UmsRoleMenuRelation;
import com.elliot.mall.model.UmsRoleMenuRelationExample;
import com.elliot.mall.model.UmsRoleResourceRelation;
import com.elliot.mall.model.UmsRoleResourceRelationExample;
import com.elliot.mall.service.UmsAdminCacheService;
import com.elliot.mall.service.UmsRoleService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.StrUtil;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {
	@Autowired
	private UmsRoleMapper roleMapper;
	@Autowired
	private UmsRoleMenuRelationMapper roleMenuRelationMapper;
	@Autowired
	private UmsRoleResourceRelationMapper roleResourceRelationMapper;
	@Autowired
	private UmsRoleDao roleDao;
	@Autowired
	private UmsAdminCacheService adminCacheService;

	@Override
	public int create(UmsRole role) {
		role.setCreateTime(new Date());
		role.setAdminCount(0);
		role.setSort(0);
		return roleMapper.insert(role);
	}

	@Override
	public int update(Long id, UmsRole role) {
		role.setId(id);
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public int delete(List<Long> ids) {
		UmsRoleExample example = new UmsRoleExample();
		example.createCriteria().andIdIn(ids);
		int count = roleMapper.deleteByExample(example);
		adminCacheService.delResourceListByRoleIds(ids);
		return count;
	}

	@Override
	public List<UmsRole> list() {
		return roleMapper.selectByExample(new UmsRoleExample());
	}

	@Override
	public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		UmsRoleExample example = new UmsRoleExample();
		if (!StrUtil.isEmpty(keyword)) {
			example.createCriteria().andNameLike("%" + keyword + "%");
		}
		return roleMapper.selectByExample(example);
	}

	@Override
	public List<UmsMenu> getMenuList(Long adminId) {
		return roleDao.getMenuList(adminId);
	}

	@Override
	public List<UmsMenu> listMenu(Long roleId) {
		return roleDao.getMenuListByRoleId(roleId);
	}

	@Override
	public List<UmsResource> listResource(Long roleId) {
		return roleDao.getResourceListByRoleId(roleId);
	}

	@Override
	public int allocMenu(Long roleId, List<Long> menuIds) {
		// 先删除原有关系
		UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleMenuRelationMapper.deleteByExample(example);
		// 批量插入新关系
		for (Long menuId : menuIds) {
			UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
			relation.setRoleId(roleId);
			relation.setMenuId(menuId);
			roleMenuRelationMapper.insert(relation);
		}
		return menuIds.size();
	}

	@Override
	public int allocResource(Long roleId, List<Long> resourceIds) {
		// 先删除原有关系
		UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleResourceRelationMapper.deleteByExample(example);
		// 批量插入新关系
		for (Long resourceId : resourceIds) {
			UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
			relation.setRoleId(roleId);
			relation.setResourceId(resourceId);
			roleResourceRelationMapper.insert(relation);
		}
		adminCacheService.delResourceListByRole(roleId);
		return resourceIds.size();
	}
}

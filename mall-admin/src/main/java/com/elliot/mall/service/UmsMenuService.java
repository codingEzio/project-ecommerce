package com.elliot.mall.service;

import com.elliot.mall.dto.UmsMenuNode;
import com.elliot.mall.model.UmsMenu;

import java.util.List;

public interface UmsMenuService {
	int create(UmsMenu umsMenu);

	int update(Long id, UmsMenu umsMenu);

	UmsMenu getItem(Long id);

	int delete(Long id);

	List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

	List<UmsMenuNode> treeList();

	int updateHidden(Long id, Integer hidden);
}

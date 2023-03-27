package com.elliot.mall.service;

import com.elliot.mall.dto.PmsProductAttributeParam;
import com.elliot.mall.dto.ProductAttrInfo;
import com.elliot.mall.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PmsProductAttributeService {
	List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

	@Transactional
	int create(PmsProductAttributeParam pmsProductAttributeParam);

	int update(Long id, PmsProductAttributeParam productAttributeParam);

	PmsProductAttribute getItem(Long id);

	@Transactional
	int delete(List<Long> ids);

	List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}

package com.elliot.mall.demo.service;

import java.util.List;

import com.elliot.mall.demo.dto.PmsBrandDto;
import com.elliot.mall.model.PmsBrand;

public interface DemoService {
	List<PmsBrand> listAllBrand();

	int createBrand(PmsBrandDto pmsBrandDto);

	int updateBrand(Long id, PmsBrandDto pmsBrandDto);

	int deleteBrand(Long id);

	List<PmsBrand> listBrand(int pageNum, int pageSize);

	PmsBrand getBrand(Long id);
}

package com.elliot.mall.service;

import com.elliot.mall.dto.PmsBrandParam;
import com.elliot.mall.model.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PmsBrandService {
	List<PmsBrand> listAllBrand();

	int createBrand(PmsBrandParam pmsBrandParam);

	@Transactional
	int updateBrand(Long id, PmsBrandParam pmsBrandParam);

	int deleteBrand(Long id);

	int deleteBrand(List<Long> ids);

	List<PmsBrand> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize);

	PmsBrand getBrand(Long id);

	int updateShowStatus(List<Long> ids, Integer showStatus);

	int updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}

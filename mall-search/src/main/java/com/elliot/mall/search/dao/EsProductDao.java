package com.elliot.mall.search.dao;

import com.elliot.mall.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EsProductDao {
	List<EsProduct> getAllEsProductList(@Param("id") Long id);
}

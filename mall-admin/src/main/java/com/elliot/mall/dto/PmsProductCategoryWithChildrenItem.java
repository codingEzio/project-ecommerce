package com.elliot.mall.dto;

import java.util.List;

import com.elliot.mall.model.PmsProductCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
	@Getter
	@Setter
	@ApiModelProperty("子级分类")
	private List<PmsProductCategory> children;
}

package com.elliot.mall.portal.domain;

import java.util.List;

import com.elliot.mall.model.PmsProductCategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PmsProductCategoryNode extends PmsProductCategory {
	@ApiModelProperty("子分类集合")
	private List<PmsProductCategoryNode> children;
}

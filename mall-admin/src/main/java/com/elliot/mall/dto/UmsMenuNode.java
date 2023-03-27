package com.elliot.mall.dto;

import java.util.List;

import com.elliot.mall.model.UmsMenu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
	@ApiModelProperty(value = "子级菜单")
	private List<UmsMenuNode> children;
}

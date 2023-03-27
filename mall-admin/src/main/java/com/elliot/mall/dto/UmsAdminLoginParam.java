package com.elliot.mall.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UmsAdminLoginParam {
	@NotEmpty
	@ApiModelProperty(value = "用户名", required = true)
	private String username;
	@NotEmpty
	@ApiModelProperty(value = "密码", required = true)
	private String password;
}

package com.elliot.mall.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmsAdminParam {
	@NotEmpty
	@ApiModelProperty(value = "用户名", required = true)
	private String username;
	@NotEmpty
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	@ApiModelProperty(value = "用户头像")
	private String icon;
	@Email
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "用户昵称")
	private String nickName;
	@ApiModelProperty(value = "备注")
	private String note;
}

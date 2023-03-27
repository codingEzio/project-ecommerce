package com.elliot.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MinioUploadDto {
	@ApiModelProperty("文件访问URL")
	private String url;
	@ApiModelProperty("文件名称")
	private String name;
}

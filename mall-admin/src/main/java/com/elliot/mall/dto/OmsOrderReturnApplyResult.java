package com.elliot.mall.dto;

import com.elliot.mall.model.OmsCompanyAddress;
import com.elliot.mall.model.OmsOrderReturnApply;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
	@Getter
	@Setter
	@ApiModelProperty(value = "公司收货地址")
	private OmsCompanyAddress companyAddress;
}

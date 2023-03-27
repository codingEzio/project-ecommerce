package com.elliot.mall.portal.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class MemberBrandAttention {
	@Id
	private String id;
	@Indexed
	private Long memberId;
	private String memberNickname;
	private String memberIcon;
	@Indexed
	private Long brandId;
	private String brandName;
	private String brandLogo;
	private String brandCity;
	private Date createTime;
}

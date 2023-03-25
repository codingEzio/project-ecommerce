package com.elliot.mall.search.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class EsProductAttributeValue implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long productAttributeId;

	@Field(type = FieldType.Keyword)
	private String value;

	@Field(type = FieldType.Keyword)
	private String name;

	private Integer type;
}

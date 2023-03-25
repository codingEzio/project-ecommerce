package com.elliot.mall.search.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
@Document(indexName = "pms")
@Setting(shards = 1, replicas = 0)

public class EsProduct {
	private static final long serialVersionUID = -1L;

	@Id
	private Long id;

	@Field(type = FieldType.Keyword)
	private String productSn;

	@Field(type = FieldType.Keyword)
	private String brandName;

	@Field(type = FieldType.Keyword)
	private String productCategoryName;

	@Field(analyzer = "ik_max_word", type = FieldType.Text)
	private String name;
	@Field(analyzer = "ik_max_word", type = FieldType.Text)
	private String subTitle;
	@Field(analyzer = "ik_max_word", type = FieldType.Text)
	private String keywords;

	private Long brandId;
	private Long productCategoryId;
	private String pic;

	private BigDecimal price;
	private Integer stock;

	private Integer newStatus;
	private Integer sale;
	private Integer promotionType;
	private Integer recommandStatus;

	private Integer sort;

	@Field(type = FieldType.Nested, fielddata = true)
	private List<EsProductAttributeValue> attrValueList;
}

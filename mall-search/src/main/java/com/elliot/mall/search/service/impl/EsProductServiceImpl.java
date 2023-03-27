package com.elliot.mall.search.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;

import com.elliot.mall.search.dao.EsProductDao;
import com.elliot.mall.search.domain.EsProduct;
import com.elliot.mall.search.domain.EsProductRelatedInfo;
import com.elliot.mall.search.repository.EsProductRepository;
import com.elliot.mall.search.service.EsProductService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EsProductServiceImpl implements EsProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

	private final EsProductDao productDao;

	private final EsProductRepository productRepository;

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public EsProductServiceImpl(EsProductDao productDao, EsProductRepository productRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.productDao = productDao;
		this.productRepository = productRepository;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	/**
	 * Imports all {@link EsProduct} objects from the database into the Elasticsearch index.
	 *
	 * @return The number of {@link EsProduct} objects imported into the Elasticsearch index.
	 */
	@Override
	public int importAll() {
		// Get a list of all products from the productDao object
		List<EsProduct> esProductList = productDao.getAllEsProductList(null);

		// Save all products in the list to the Elasticsearch index and get an iterator for the iterable result
		Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
		Iterator<EsProduct> iterator = esProductIterable.iterator();

		// Initialize the result to 0
		int result = 0;

		// Iterate over the iterable result and count the number of objects returned
		while (iterator.hasNext()) {
			result++;
			iterator.next();
		}

		// Return the result
		return result;
	}


	/**
	 * Deletes the {@link EsProduct} object with the given ID from the Elasticsearch index.
	 *
	 * @param id The ID of the product to delete.
	 */
	@Override
	public void delete(Long id) {
		// Delete the product with the given ID from the Elasticsearch index
		productRepository.deleteById(id);
	}


	/**
	 * Creates a new {@link EsProduct} object in the Elasticsearch index with the given ID.
	 *
	 * @param id The ID to use for the new product.
	 *
	 * @return The newly created {@link EsProduct} object, or null if no product was found with the given ID.
	 */
	@Override
	public EsProduct create(Long id) {
		// Initialize the result to null
		EsProduct result = null;

		// Get a list of all products with the given ID from the productDao object
		List<EsProduct> productList = productDao.getAllEsProductList(id);

		// If the productList is not empty, save the first product in the list to the Elasticsearch index
		if (!CollectionUtils.isEmpty(productList)) {
			EsProduct product = productList.get(0);
			result = productRepository.save(product);
		}

		// Return the result
		return result;
	}

	/**
	 * Deletes a list of products from the Elasticsearch index.
	 *
	 * @param ids The list of product IDs to delete.
	 */
	@Override
	public void delete(List<Long> ids) {
		// Check if the IDs list is not empty
		if (!CollectionUtils.isEmpty(ids)) {
			// Create a new empty list of EsProduct objects
			List<EsProduct> productList = new ArrayList<>();

			// Iterate over each ID in the list and create a new EsProduct object with that ID
			for (Long id: ids) {
				EsProduct product = new EsProduct();
				product.setId(id);
				productList.add(product);
			}

			// Delete all products in the productList from the Elasticsearch index
			productRepository.deleteAll(productList);
		}
	}


	/**
	 * Searches for products in an Elasticsearch index based on a given keyword.
	 *
	 * @param keyword  The search term to use when searching for products.
	 * @param pageNum  The page number of results to return.
	 * @param pageSize The number of results to return per page.
	 *
	 * @return A page of {@link EsProduct} objects that match the search criteria.
	 */
	@Override
	public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
		// Create a Pageable object based on the given page number and page size
		Pageable pageable = PageRequest.of(pageNum, pageSize);

		// Call the findByNameOrSubTitleOrKeywords method on the productRepository object, passing in the keyword and pageable parameters
		return productRepository.findByNameOrSubTitleOrKeywords(
				keyword,
				keyword,
				keyword,
				pageable
		);
	}


	/**
	 * Search for products matching the specified criteria.
	 *
	 * @param keyword           The search keyword (may be null or empty to match all products).
	 * @param brandId           The ID of the brand to filter by (may be null to not filter by brand).
	 * @param productCategoryId The ID of the product category to filter by (may be null to not filter by category).
	 * @param pageNum           The page number to retrieve (0-based).
	 * @param pageSize          The maximum number of products to retrieve per page.
	 * @param sort              The sort order to use (1 = by sale count descending, 2 = by price ascending,
	 *                          3 = by price descending, other = by ID descending).
	 *
	 * @return A page of products matching the specified criteria.
	 */
	@Override
	public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
		// Create a pageable object based on the requested page and page size.
		Pageable pageable = PageRequest.of(pageNum, pageSize);

		// Create a search query builder for building the Elasticsearch query.
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();

		// Set the pageable parameter on the search query builder.
		searchQueryBuilder.withPageable(pageable);

		// If either the brand ID or the product category ID is specified, add a filter to the query builder.
		if (brandId != null || productCategoryId != null) {
			// Create a boolean query builder to combine the brand and category filters.
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			// If the brand ID is specified, add a term query for the brand ID to the boolean query builder.
			if (brandId != null) {
				boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
			}

			// If the product category ID is specified, add a term query for the category ID to the boolean query builder.
			if (productCategoryId != null) {
				boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
			}

			// Add the boolean query builder to the search query builder as a filter.
			searchQueryBuilder.withFilter(boolQueryBuilder);
		}

		// If a search keyword is specified, add a function score query to the search query builder based on the keyword.
		if (StrUtil.isEmpty(keyword)) {
			// If no keyword is specified, add a match all query to the search query builder.
			searchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
		} else {
			// If a keyword is specified, create a list of filter function builders to use in the function score query.
			List<FunctionScoreQueryBuilder.FilterFunctionBuilder> builderList = new ArrayList<>();

			// Add a filter function builder for the name field with a weight of 10.
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("name", keyword),
					ScoreFunctionBuilders.weightFactorFunction(10)
			));

			// Add a filter function builder for the subTitle field with a weight of 5.
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("subTitle", keyword),
					ScoreFunctionBuilders.weightFactorFunction(5)
			));

			// Add a filter function builder for the keywords field with a weight of 2.
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("keywords", keyword),
					ScoreFunctionBuilders.weightFactorFunction(2)
			));

			// Convert the list of filter function builders to an array and create the function score query.
			FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[builderList.size()];
			builderList.toArray(builders);

			FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
					.scoreMode(FunctionScoreQuery.ScoreMode.SUM)
					.setMinScore(2);

			// Add the function score query to the search query builder.
			searchQueryBuilder.withQuery(functionScoreQueryBuilder);
		}

		// Set the sort order on the search query builder based on the requested sort parameter.
		switch (sort) {
			case 1:
				searchQueryBuilder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
				break;
			case 2:
				searchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
				break;
			case 3:
				searchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
				break;
			default:
				searchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
				break;
		}

		// Add a secondary sort order by ID in descending order to ensure consistent results.
		searchQueryBuilder.withSorts(SortBuilders.fieldSort("id").order(SortOrder.DESC));

		// Build the search query and log the DSL.
		NativeSearchQuery searchQuery = searchQueryBuilder.build();
		LOGGER.info("DSL: {}", searchQuery.getQuery().toString());

		// Execute the search query and return the resulting page of products.
		SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
		if (searchHits.getTotalHits() <= 0) {
			return new PageImpl<>(ListUtil.empty(), pageable, 0);
		}

		List<EsProduct> searchProductList = searchHits.stream()
				.map(SearchHit::getContent)
				.collect(Collectors.toList());

		return new PageImpl<>(searchProductList, pageable, searchHits.getTotalHits());
	}


	/**
	 * Get recommended products based on the provided product's name, brand and category
	 *
	 * @param id       product id
	 * @param pageNum  page number
	 * @param pageSize page size
	 *
	 * @return a page of recommended products
	 */
	@Override
	public Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		List<EsProduct> productList = productDao.getAllEsProductList(id);

		if (productList.size() > 0) {
			EsProduct product = productList.get(0);

			String keyword = product.getName();
			Long brandId = product.getBrandId();
			Long productCategoryId = product.getProductCategoryId();

			// Set up function score query builder with weight factors for various search fields
			List<FunctionScoreQueryBuilder.FilterFunctionBuilder> builderList = new ArrayList<>();
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("name", keyword),
					ScoreFunctionBuilders.weightFactorFunction(8)
			));
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("subTitle", keyword),
					ScoreFunctionBuilders.weightFactorFunction(2)
			));
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.matchQuery("keywords", keyword),
					ScoreFunctionBuilders.weightFactorFunction(2)
			));
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.termQuery("brandId", brandId),
					ScoreFunctionBuilders.weightFactorFunction(5)
			));
			builderList.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(
					QueryBuilders.termQuery("productCategoryId", productCategoryId),
					ScoreFunctionBuilders.weightFactorFunction(3)
			));

			FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[builderList.size()];
			builderList.toArray(builders);

			FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
					.scoreMode(FunctionScoreQuery.ScoreMode.SUM)
					.setMinScore(2);

			// Set up boolean query builder to exclude the original product from search results
			BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
			boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", id));

			// Set up search query builder and add function score query and boolean query to it
			NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
			searchQueryBuilder.withQuery(functionScoreQueryBuilder);
			searchQueryBuilder.withFilter(boolQueryBuilder);
			searchQueryBuilder.withPageable(pageable);

			// Build and execute search query
			NativeSearchQuery searchQuery = searchQueryBuilder.build();
			LOGGER.info("DSL: {}", searchQuery.getQuery().toString());

			SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
			if (searchHits.getTotalHits() <= 0) {
				return new PageImpl<>(ListUtil.empty(), pageable, 0);
			}

			List<EsProduct> searchProductList = searchHits.stream()
					.map(SearchHit::getContent)
					.collect(Collectors.toList());

			return new PageImpl<>(searchProductList, pageable, searchHits.getTotalHits());
		}

		return new PageImpl<>(ListUtil.empty());
	}


	/**
	 * Searches for related product information based on the given keyword.
	 *
	 * @param keyword the keyword to search for
	 *
	 * @return an object containing the related product information
	 */
	@Override
	public EsProductRelatedInfo searchRelatedInfo(String keyword) {
		// Build a query using a NativeSearchQueryBuilder.
		NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();

		// If the keyword is empty, add a match all query to the search query. Otherwise, add a multi-match query.
		if (StrUtil.isEmpty(keyword)) {
			searchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
		} else {
			searchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyword));
		}

		// Add an aggregation for the brand names and another aggregation for the product category names.
		searchQueryBuilder.withAggregations(AggregationBuilders.terms("brandNames").field("brandName"));
		searchQueryBuilder.withAggregations(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));

		// Add an aggregation for the product attribute values using a nested aggregation.
		AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues", "attrValueList")
				.subAggregation(
						AggregationBuilders.filter("productAttrs", QueryBuilders.termQuery("attrValueList.type", 1))
								.subAggregation(
										AggregationBuilders.terms("productAttrIds").field("attrValueList.productAttributeId")
												.subAggregation(AggregationBuilders.terms("productAttrValues").field("attrValueList.value"))
												.subAggregation(AggregationBuilders.terms("productAttrNames").field("attrValueList.name"))
								)
				);
		searchQueryBuilder.withAggregations(aggregationBuilder);

		// Build the search query and execute it using ElasticsearchRestTemplate.
		NativeSearchQuery searchQuery = searchQueryBuilder.build();
		SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);

		// Convert the search hits to an EsProductRelatedInfo object and return it.
		return convertProductRelatedInfo(searchHits);
	}


	/**
	 * Overall, this method is useful when we want to extract specific information from the search result aggregations and present it in a more structured and organized way. Instead of just returning a raw search result, we can create a custom object with only the information we want, which can be useful for displaying information to users or for further processing.
	 */
	private EsProductRelatedInfo convertProductRelatedInfo(SearchHits<EsProduct> response) {
		EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();

		Map<String, Aggregation> aggregationMap = ((Aggregations) response.getAggregations().aggregations()).asMap();

		// Get brand names
		Aggregation brandNames = aggregationMap.get("brandNames");
		List<String> brandNameList = new ArrayList<>();
		for (int i = 0; i < ((Terms) brandNames).getBuckets().size(); i++) {
			brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
		}
		productRelatedInfo.setBrandNames(brandNameList);

		// Get product category names
		Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
		List<String> productCategoryNameList = new ArrayList<>();
		for (int i = 0; i < ((Terms) productCategoryNames).getBuckets().size(); i++) {
			productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
		}
		productRelatedInfo.setProductCategoryNames(productCategoryNameList);

		// Get product attributes
		Aggregation productAttrs = aggregationMap.get("allAttrValues");
		List<? extends Terms.Bucket> attrIds = ((ParsedLongTerms) ((ParsedFilter) ((ParsedNested) productAttrs)
				.getAggregations().asMap().get("productAttrs"))
				.getAggregations().asMap().get("productAttrIds")).getBuckets();
		List<EsProductRelatedInfo.ProductAttr> attrList = new ArrayList<>();
		for (Terms.Bucket attrId: attrIds) {
			EsProductRelatedInfo.ProductAttr productAttr = new EsProductRelatedInfo.ProductAttr();

			productAttr.setAttrId((Long) attrId.getKey());
			List<String> attrValueList = new ArrayList<>();

			List<? extends Terms.Bucket> attrValues = ((ParsedStringTerms) attrId.getAggregations().get("attrValues"))
					.getBuckets();
			List<? extends Terms.Bucket> attrNames = ((ParsedStringTerms) attrId.getAggregations().get("attrNames"))
					.getBuckets();

			for (Terms.Bucket attrValue: attrValues) {
				attrValueList.add(attrValue.getKeyAsString());
			}

			productAttr.setAttrValues(attrValueList);

			if (!CollectionUtils.isEmpty(attrNames)) {
				String attrName = attrNames.get(0).getKeyAsString();
				productAttr.setAttrName(attrName);
			}

			attrList.add(productAttr);
		}

		productRelatedInfo.setProductAttrs(attrList);

		return productRelatedInfo;
	}
}

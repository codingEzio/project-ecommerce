package com.elliot.mall.common.api;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

/**
 * Why is this (to achieve pagination) necessary?
 * Pagination allows users to divide a large set of data into smaller, more
 * manageable chunks. This is particularly useful when dealing with large
 * datasets that would otherwise cause performance issues or lead to memory
 * exhaustion. Instead of returning the entire dataset, pagination allows
 * developers to return a subset of the data that the user requests, which
 * can help to improve application performance and reduce server load.
 */
public class CommonPage <T> {
	// Field

	private Integer pageNum;
	private Integer pageSize;
	private Integer totalPage;
	private Long total;
	private List<T> list;

	// Getter and Setter

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	// Other methods

	public static <T> CommonPage<T> restPage(List<T> list) {
		CommonPage<T> result = new CommonPage<T>();
		PageInfo<T> pageInfo = new PageInfo<T>(list);

		result.setTotalPage(pageInfo.getPages());
		result.setPageNum(pageInfo.getPageNum());
		result.setPageSize(pageInfo.getPageSize());
		result.setTotal(pageInfo.getTotal());
		result.setList(pageInfo.getList());

		return result;
	}

	public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
		CommonPage<T> result = new CommonPage<T>();

		result.setTotalPage(pageInfo.getTotalPages());
		result.setPageNum(pageInfo.getNumber());
		result.setPageSize(pageInfo.getSize());
		result.setTotal(pageInfo.getTotalElements());
		result.setList(pageInfo.getContent());

		return result;
	}
}

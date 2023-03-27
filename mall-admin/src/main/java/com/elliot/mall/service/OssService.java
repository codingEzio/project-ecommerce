package com.elliot.mall.service;

import com.elliot.mall.dto.OssCallbackResult;
import com.elliot.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

public interface OssService {
	OssPolicyResult policy();

	OssCallbackResult callback(HttpServletRequest request);
}

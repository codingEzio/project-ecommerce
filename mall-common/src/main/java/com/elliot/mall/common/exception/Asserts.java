package com.elliot.mall.common.exception;

import com.elliot.mall.common.api.IErrorCode;

public class Asserts {
	public static void fail(String message) {
		throw new APIException(message);
	}

	public static void fail(IErrorCode errorCode) {
		throw new APIException(errorCode);
	}
}

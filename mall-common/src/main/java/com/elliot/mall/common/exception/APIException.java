package com.elliot.mall.common.exception;

import com.elliot.mall.common.api.IErrorCode;

public class APIException extends RuntimeException {
	private IErrorCode errorCode;

	public APIException(IErrorCode errorCode) {
		super(errorCode.getMessage());

		this.errorCode = errorCode;
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(Throwable cause) {
		super(cause);
	}

	public APIException(String message, Throwable cause) {
		super(message, cause);
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}
}

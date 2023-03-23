package com.elliot.mall.common.exception;

import com.elliot.mall.common.api.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseBody
	@ExceptionHandler(value = APIException.class)
	public CommonResult handle(APIException exception) {
		if (exception.getErrorCode() != null) {
			return CommonResult.failed(exception.getErrorCode());
		}

		return CommonResult.failed(exception.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public CommonResult handleValidException(MethodArgumentNotValidException exception) {
		return getCommonResult(exception);
	}

	@ResponseBody
	@ExceptionHandler(value = BindException.class)
	public CommonResult handleValidException(BindException exception) {
		return getCommonResult(exception);
	}

	private CommonResult getCommonResult(BindException exception) {
		BindingResult result = exception.getBindingResult();
		String message = null;

		if (result.hasErrors()) {
			FieldError fieldError = result.getFieldError();

			if (fieldError != null) {
				message = fieldError.getField() + fieldError.getDefaultMessage();
			}
		}

		return CommonResult.validateFailed(message);
	}
}

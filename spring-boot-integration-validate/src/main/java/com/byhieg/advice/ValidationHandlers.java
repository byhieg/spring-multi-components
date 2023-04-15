package com.byhieg.advice;

import com.byhieg.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
@RestControllerAdvice
@Slf4j
public class ValidationHandlers {

	@ExceptionHandler(value = BindException.class)
	public ApiResponse<String> errorHandler(BindException bindException) {
		BindingResult bindingResult = bindException.getBindingResult();
		String errorMsg = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
		return ApiResponse.error(errorMsg, null);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ApiResponse<String> errorHandler(ConstraintViolationException constraintViolationException) {
		String errorMsg = constraintViolationException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
		return ApiResponse.error(errorMsg, null);
	}
	
}

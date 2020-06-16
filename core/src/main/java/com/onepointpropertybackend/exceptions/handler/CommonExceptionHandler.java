package com.onepointpropertybackend.exceptions.handler;


import com.onepointpropertybackend.exceptions.models.ErrorBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
//Reference: https://howtodoinjava.com/spring-boot2/spring-rest-request-validation/
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<List<ErrorBean>> handleAllExceptions(Exception ex, WebRequest request) {
		List<ErrorBean> details = new ArrayList<>();

		details.add(ErrorBean.builder().key(HttpStatus.INTERNAL_SERVER_ERROR.name()).message(ex.getMessage())
				.time(new Date()).build());

		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorBean>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

		List<ErrorBean> details = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(ErrorBean.builder().key("FIELD_VALIDATION_FAILED").message(error.getDefaultMessage())
					.time(new Date()).build());
		}

		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

}

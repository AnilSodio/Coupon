/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.apiv2.web;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	 @ExceptionHandler(value = { ConstraintViolationException.class })
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 public String handleResourceNotFoundException(ConstraintViolationException e) {
	      Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	      StringBuilder strBuilder = new StringBuilder();
	      for (ConstraintViolation<?> violation : violations ) {
	           strBuilder.append(violation.getMessage() + "\n");
	      }
	      return StringUtils.stripEnd(strBuilder.toString(), "\n");
	 }
	 
	 @ExceptionHandler(value = { MethodArgumentNotValidException.class })
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	 public String handleResourceNotFoundException(MethodArgumentNotValidException e) {
		 List<ObjectError> errorList= e.getBindingResult().getAllErrors();
		 StringBuilder strBuilder = new StringBuilder();
		 for (ObjectError error : errorList ) {
			 strBuilder.append(error.getDefaultMessage() + "\n");
		 }
		 return StringUtils.stripEnd(strBuilder.toString(), "\n");
	 }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
	}
}

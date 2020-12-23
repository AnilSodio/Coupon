package vc.thinker.apiv2.http.response;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public  class SimpleResponse {
	private MessageSource messageSource;
	private Locale locale;
	
	public SimpleResponse(){
		
	}
	public SimpleResponse(MessageSource messageSource,HttpServletRequest request){
		this.messageSource=messageSource;
		this.locale= RequestContextUtils.getLocale(request);
	}
	
	public SimpleResponse(boolean success, String error, String errorDescription) {
		super();
		this.success = success;
		this.error = error;
		this.errorDescription = errorDescription;
	}
	private boolean success = true;
	private String error;
	@JSONField(name="error_description")
	@JsonProperty(value="error_description")
	private String errorDescription;
	
	/**
	 * 设置错误信息
	 * @param error
	 * @param errorDescription
	 */
	public void setErrorInfo(String error, String errorDescription){
		this.success = false;
		this.error = error;
		this.errorDescription = errorDescription;
	}
	
	/**
	 * 设置错误信息
	 * @param error
	 * @param errorDescription
	 */
	public void setErrorInfo(String error, String errorMessageCode,String defaultMessage){
		this.success = false;
		this.error = error;
		this.errorDescription = messageSource.getMessage(errorMessageCode, null, defaultMessage, locale);
	}
	
	/**
	 * 设置错误信息
	 * @param error
	 * @param errorDescription
	 */
	public void setErrorInfo(String error, String errorMessageCode,Object[] args,String defaultMessage){
		this.success = false;
		this.error = error;
		this.errorDescription = messageSource.getMessage(errorMessageCode, args, defaultMessage, locale);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}

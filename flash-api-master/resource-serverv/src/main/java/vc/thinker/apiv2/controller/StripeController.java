/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.EphemeralKey;
import com.stripe.net.RequestOptions;
import com.stripe.net.RequestOptions.RequestOptionsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import vc.thinker.apiv2.http.response.SimpleResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.oauth.AuthContants;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayException;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.stripe.StripeConfig;
import vc.thinker.pay.stripe.StripePayHandler;

/**
 * stripe 支付相关
 * @author ThinkGem
 * @version 2013-5-31
 */
@RestController
@RequestMapping(value = "/api/stripe", produces = { APPLICATION_JSON_VALUE })
@Api(value = "个人用户操作类", description = "提供与个人用户相关的api")
public class StripeController extends BaseController{

	private static final Logger log=LoggerFactory.getLogger(StripeController.class);
	
	@Autowired
	private PayHandlerFactory handlerFactory;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private MessageSource messageSource;
	
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/get_ephemeral_key", method = { RequestMethod.POST })
	@ApiOperation(value = "得到ephemeral_key", notes = "得到ephemeral_key")
	@ResponseBody
	public SingleResponse<String> getEphemeralKey(
			HttpServletRequest request,
			HttpServletResponse response,
			@ApiParam(value = "apiVersion", required = true) @RequestParam("apiVersion") String apiVersion,
			@UserAuthentication User user){
		SingleResponse<String>  resp=new SingleResponse<>();
		Long uid = Long.valueOf(user.getUsername());
		
		StripePayHandler payHandler=handlerFactory.getPayHandler(PayChannel.STRIPE);
		StripeConfig config=payHandler.getConfig("stripe");
		//config.setApiKey("sk_test_tcRO0oPldZyfdllCWVGzGXwU");
    	RequestOptions requestOptions = (new RequestOptionsBuilder()).
    			setApiKey(config.getApiKey()).
    			setStripeVersion(apiVersion).build();
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("customer", memberService.getStripeCustomerId(uid, config.getApiKey()));
		EphemeralKey key;
		try {
			key = EphemeralKey.create(params, requestOptions);
			resp.setItem(key.toJson());
		} catch (StripeException e) {
			log.error("",e);
			resp.setErrorInfo("406", e.getMessage());
		} 
    	return resp;
	}
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_END_USER+"')")
	@RequestMapping(value = "/del_bank_card", method = { RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value = "删除银行卡", notes = "删除银行卡")
	public SimpleResponse deleteBankCard(
			HttpServletRequest request,
			@UserAuthentication User user,
			@ApiParam(value = "card", required = true) @RequestParam("card") String card
			) {
		long uid = Long.valueOf(user.getUsername());
		SimpleResponse resp = new SimpleResponse(messageSource,request);
		
		try {
			memberService.delStripeCard(uid, card);
		} catch (PayException e) {
			logger.error("删除银行卡失败",e);
			resp.setErrorInfo("406","member.del.card","操作失败");
			return resp;
		}
		return resp;
	}
}

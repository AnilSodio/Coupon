/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.apiv2.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.JedisPool;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.PaymentConstants;
import vc.thinker.cabbage.money.service.MoneyService;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.exception.DepositException;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayException;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.alipay.AlipayPayHandler;
import vc.thinker.pay.alipay.AlipayPayNotify;
import vc.thinker.pay.cashfree.CashfreeNotify;
import vc.thinker.pay.cashfree.CashfreePayHandler;
import vc.thinker.pay.fondy.FondyNotify;
import vc.thinker.pay.fondy.FondyPayHandler;
import vc.thinker.pay.weixin.WeixinPayHandler;
import vc.thinker.pay.weixin.api.PayUtils;
import vc.thinker.pay.weixin.bean.WeixinPayNotify;

/**
 * 订单 Controller
 * 
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class PayController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(PayController.class);

	@Autowired
	private PayHandlerFactory handlerFactory;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MoneyService moneyService;

	@Autowired
	private JedisPool jedisPool;

//	/**
//	 * 微信通知
//	 */
//	@RequestMapping(value = "/weixin/notify/test")
//	@ResponseBody
//	public String weixinNotify(HttpServletRequest request, HttpServletResponse response,String payOrderCode) {
//		tripService.completePay(payOrderCode, UUID.randomUUID().toString());
//		return "成功";
//	}

	/**
	 * 微信通知
	 */
	@RequestMapping(value = "/weixin/notify", produces = "application/xml")
	@ResponseBody
	public String weixinNotify(HttpServletRequest request, HttpServletResponse response, Model model) {
		WeixinPayHandler payHandler = handlerFactory.getPayHandler(PayChannel.WEIXIN);

		try {
			WeixinPayNotify payNotify = payHandler.getPayNotify(request);
			String tradeNo = payNotify.getOut_trade_no();

			if (payHandler.verifyNotify(payNotify)) {
				log.debug("收到微信回调：" + payNotify.getResult_code());
				if (payNotify.isPaySuccess()) {
					try {
						String payType = payNotify.getReqAttach();

						BigDecimal cashFee = new BigDecimal(Integer.parseInt(payNotify.getCash_fee()) / 100d);
						// 行程支付通知
						if (CommonConstants.PAY_TYPE_DEPOSIT.equals(payType)) {
							memberService.completeDepositPay(tradeNo, payNotify.getTransaction_id(),
									new BigDecimal(payNotify.getCash_fee()));
						} else if (CommonConstants.PAY_TYPE_VIP.equals(payType)) {
							String sn = tradeNo;
							memberService.completeVIPPay(sn, payNotify.getTransaction_id(), cashFee);
						} else if (CommonConstants.PAY_TYPE_BALANCE.equals(payType)) {
							String sn = tradeNo;
							moneyService.rechargeMoney(sn, payNotify.getTransaction_id(), cashFee);
						} else if (CommonConstants.PAY_TYPE_BASIC_COST.equals(payType)) {
							memberService.completeBasicCostPay(tradeNo, payNotify.getTransaction_id(), cashFee);
						} else if (CommonConstants.PAY_TYPE_PB_BUY.equals(payType)) {
							memberService.completePbPay(tradeNo, payNotify.getTransaction_id(), cashFee);
						}
					} catch (DepositException e) {
						log.info("押金[{}]已经完成支付，直接返回微信成功", tradeNo);
						return PayUtils.generatePaySuccessReplyXML();
					} catch (ServiceException e) {
						log.error("调用完成订单支付失败，订单号[{}],原因[{}]", tradeNo, e.getMessage());
						return PayUtils.generatePayFailReplyXML(e.getMessage());
					}
					log.info("订单号[{}],支付完成", tradeNo);
				}
				return PayUtils.generatePaySuccessReplyXML();
			} else {
				log.error("订单[{}]收到微信回调,验证签名失败", tradeNo);
				return PayUtils.generatePayFailReplyXML("签名失败");
			}
		} catch (PayException e) {
			log.error("调用完成订单支付失败，订单号[{}],原因[{}]", e.getMessage());
			return PayUtils.generatePayFailReplyXML(e.getMessage());
		}

	}

	/**
	 * 支付宝通知
	 */
	@RequestMapping(value = "/alipay/notify")
	@ResponseBody
	public String alipayNotify(HttpServletRequest request, HttpServletResponse response, Model model) {
		AlipayPayHandler payHandler = handlerFactory.getPayHandler(PayChannel.ALIPAY);

		AlipayPayNotify payNotify = payHandler.getPayNotify(request);
		// 支付宝订单号
		String tradeNo = payNotify.getTradeNo();
		// 平台订单号
		String outTradeNo = payNotify.getOutTradeNo();

		log.info("收到支付宝的回调,支付宝订单号：【" + tradeNo + "】,平台订单号:【" + outTradeNo + "】");

		try {
			if (payHandler.verifyNotify(payNotify)) {

				log.info("支付结果为：【" + payNotify.isPaySuccess() + "】");

				if (payNotify.isPaySuccess()) {
					try {

						String payType = payNotify.getReqAttach();
						BigDecimal cashFee = new BigDecimal(payNotify.getTotalFee());
						if (CommonConstants.PAY_TYPE_DEPOSIT.equals(payType)) {
							memberService.completeDepositPay(outTradeNo, tradeNo,
									new BigDecimal(payNotify.getTotalFee()));
						} else if (CommonConstants.PAY_TYPE_VIP.equals(payType)) {
							String sn = outTradeNo;
							memberService.completeVIPPay(sn, tradeNo, cashFee);
						} else if (CommonConstants.PAY_TYPE_BALANCE.equals(payType)) {
							String sn = outTradeNo;
							moneyService.rechargeMoney(sn, tradeNo, cashFee);
						} else if (CommonConstants.PAY_TYPE_BASIC_COST.equals(payType)) {
							memberService.completeBasicCostPay(outTradeNo, tradeNo, cashFee);
						} else if (CommonConstants.PAY_TYPE_PB_BUY.equals(payType)) {
							memberService.completePbPay(tradeNo, tradeNo, cashFee);
						}
					} catch (ServiceException e) {
						log.error("调用完成订单支付失败，订单号[{}],原因[{}]", tradeNo, e.getMessage());
						return PayUtils.generatePayFailReplyXML(e.getMessage());
					}
					log.info("订单号[{}],支付完成", tradeNo);
				}
				return "success";
			} else {
				log.error("订单[{}]收到支付宝回调,验证签名失败", tradeNo);
				return "fail";
			}
		} catch (ServiceException e) {
			log.error("调用完成订单支付失败，订单号[{}],原因[{}]", tradeNo, e.getMessage());
			return "fail";
		}
	}

	@RequestMapping(value = "/cashfree/notify")
	@ResponseBody
	public String cashPayNotify(HttpServletRequest request, Model model) throws PayException {

		Map<String, String[]> param = request.getParameterMap();
		log.info("收到 CashFree 回调:[{}]", JSON.toJSONString(param));

		CashfreePayHandler payHandler = handlerFactory.getPayHandler(PayChannel.CASHFREE);

		CashfreeNotify payNotify = payHandler.getPayNotify(request);
		payNotify.setConfigMark(PaymentConstants.PAYMENT_MARK_CASHFREE);

		String orderId = payNotify.getOrderId();
		String referenceId = payNotify.getReferenceId();
		String txStatus = payNotify.getTxStatus();
		String paySuccess = payNotify.getPaySuccess();

		log.info("收到 CashFree 的支付回调:[{}], 外部订单号:[{}], 交易状态[{}], 支付状态:[{}]", orderId, referenceId, txStatus, paySuccess);

		if (!CommonConstants.BIG_SUCCESS.equals(txStatus)) {
			log.info("CashFree 支付失败");
			return CommonConstants.FAIL;
		}

		if (!(payHandler.verifyNotify(payNotify))) {
			logger.info("CashFree 签名错误");
			return CommonConstants.FAIL;
		}

		try {
			// 支付类型
			String payType = orderId.split("-")[0];
			// 支付金额
			String orderAmount = payNotify.getOrderAmount();
			// 完成支付
			completePay(payType, orderId, referenceId, orderAmount);
			logger.info("CashFree 订单处理完成:[{}]", orderId);
			return CommonConstants.SUCCESS;
		} catch (ServiceException e) {
			log.info("调用完成订单支付失败，订单号[{}],原因[{}]", orderId, e.getMessage());
			return CommonConstants.FAIL;
		}
	}

//	@RequestMapping(value = "/fondy/notify")
//	@ResponseBody
//	public Boolean fondyPayNotify(HttpServletRequest request, Model model) throws PayException {
//		
//		FondyPayHandler payHandler = handlerFactory.getPayHandler(PayChannel.FONDY);
//		
//		FondyNotify payNotify = payHandler.getPayNotify(request);
//		payNotify.setConfigMark(PaymentConstants.PAYMENT_MARK_CASHFREE);
//		// 订单号
//		String orderId = payNotify.getOrder_id();
//		String paymentId = payNotify.getPayment_id();
//		String status = payNotify.getOrder_status();
//		String amount = payNotify.getAmount();
//		
//		log.info("订单号:[{}], 外部订单号[{}],支付状态: [{}],(approved 为支付成功)", orderId, paymentId, status);
//
////		String returnUrl = memberService.getReturnUrl(Long.valueOf(orderId.split("-")[1]), jedisPool);
////		log.info("returnUrl:[{}]",returnUrl);
//		
//		if (!"approved".equals(status)) {
//			log.info("订单支付失败");
////			model.addAttribute("returnUrl",returnUrl+"&result=false");
////			return new ModelAndView("result_notify");
//			throw new PayException();
//		}
//		if (!payHandler.verifyNotify(request)) {
//			log.info("验证签名失败");
////			model.addAttribute("returnUrl",returnUrl+"&result=false");
////			return new ModelAndView("result_notify");
//			throw new PayException();
//		}
//		try {
//			completePay(orderId.split("-")[0], orderId, paymentId, amount);
////			model.addAttribute("returnUrl",returnUrl+"&result=true");
////			return new ModelAndView("result_notify");
//			return true;
//		} catch (ServiceException e) {
//			log.info("调用完成订单支付失败，订单号[{}],原因[{}]", orderId, e.getMessage());
////			model.addAttribute("returnUrl",returnUrl+"&result=false");
////			return new ModelAndView("result_notify");
////			return false;
//			throw new PayException();
//		}
//	}

	@RequestMapping(value = "/fondy/notify")
	@ResponseBody
	public ModelAndView fondyPayNofity(HttpServletRequest request, Model model) throws PayException {

		Map<String, String[]> param = request.getParameterMap();
		log.info("收到 payNotify 回调:[{}]", JSON.toJSONString(param));

		FondyPayHandler payHandler = handlerFactory.getPayHandler(PayChannel.FONDY);

		FondyNotify payNotify = payHandler.getPayNotify(request);

		payNotify.setConfigMark(PaymentConstants.PAYMENT_MARK_CASHFREE);
		// 订单号
		String orderId = payNotify.getOrder_id();
		String paymentId = payNotify.getPayment_id();
		String status = payNotify.getOrder_status();
		String amount = payNotify.getAmount();

		log.info("订单号:[{}], 外部订单号[{}],支付状态: [{}],(approved 为支付成功)", orderId, paymentId, status);

		String returnUrl = memberService.getReturnUrl(Long.valueOf(orderId.split("-")[1]), jedisPool);

		if (!"approved".equals(status)) {
			model.addAttribute("returnUrl", returnUrl + "&result=false");
			return new ModelAndView("result_notify");
		}

		if (!payHandler.verifyNotify(request)) {
			model.addAttribute("returnUrl", returnUrl + "&result=false");
			return new ModelAndView("result_notify");
		}

		try {
			completePay(orderId.split("-")[0], orderId, paymentId, amount);
			model.addAttribute("returnUrl", returnUrl + "&result=true");
			return new ModelAndView("result_notify");

		} catch (ServiceException e) {
			log.info("调用完成订单支付失败，订单号[{}],原因[{}]", orderId, e.getMessage());
			model.addAttribute("returnUrl", returnUrl + "&result=false");
			return new ModelAndView("result_notify");
		}
	}

	@RequestMapping(value = "/returnUrl")
	@ResponseBody
	public Boolean returnUrl(HttpServletRequest request) throws PayException {
		Map<String, String[]> param = request.getParameterMap();
		log.info("收到 returnUrl 回调:[{}]", JSON.toJSONString(param));
		try {
//			createFondyPay(request);
			return true;
		} catch (ServiceException e) {
			throw e;
		}
	}

	public void createFondyPay(HttpServletRequest request) throws PayException {

		FondyPayHandler payHandler = handlerFactory.getPayHandler(PayChannel.FONDY);

		FondyNotify payNotify = payHandler.getPayNotify(request);

		payNotify.setConfigMark(PaymentConstants.PAYMENT_MARK_CASHFREE);
		// 订单号
		String orderId = payNotify.getOrder_id();
		String paymentId = payNotify.getPayment_id();
		String status = payNotify.getOrder_status();
		String amount = payNotify.getAmount();

		log.info("订单号:[{}], 外部订单号[{}],支付状态: [{}],(approved 为支付成功)", orderId, paymentId, status);

		if (!"approved".equals(status)) {
			throw new ServiceException("订单支付失败");
		}

		if (StringUtils.isBlank(orderId)) {
			throw new ServiceException("订单号为空");
		}

		if (!payHandler.verifyNotify(request)) {
			throw new ServiceException("验证签名失败");
		}

		try {

			completePay(orderId.split("-")[0], orderId, paymentId, amount);

		} catch (ServiceException e) {
			log.info("调用完成订单支付失败，订单号[{}],原因[{}]", orderId, e.getMessage());
		}
	}

	@RequestMapping(value = "/applepay/notify")
	@ResponseBody
	public void applePayNotify(HttpServletRequest request) throws PayException {
		Map<String, String[]> param = request.getParameterMap();
		log.info("收到 Apple Pay 支付回调:[{}], [{}], [{}]", JSON.toJSONString(param));
	}

	public void completePay(String payType, String orderId, String referenceId, String amount) {

		switch (payType) {
		case CommonConstants.PAY_TYPE_DEPOSIT:
			memberService.completeDepositPay(orderId, referenceId, new BigDecimal(amount));
			break;
		case CommonConstants.PAY_TYPE_VIP:
			memberService.completeVIPPay(orderId, referenceId, new BigDecimal(amount));
			break;
		case CommonConstants.PAY_TYPE_BALANCE:
			moneyService.rechargeMoney(orderId, referenceId, new BigDecimal(amount));
			break;
		case CommonConstants.PAY_TYPE_BASIC_COST:
			memberService.completeBasicCostPay(orderId, referenceId, new BigDecimal(amount));
			break;
		case CommonConstants.PAY_TYPE_PB_BUY:
			memberService.completePbPay(orderId, referenceId, new BigDecimal(amount));
			break;
		default:
			log.info("无效类型:[{}]", payType);
			throw new ServiceException("无效类型:" + payType);
		}
	}

}

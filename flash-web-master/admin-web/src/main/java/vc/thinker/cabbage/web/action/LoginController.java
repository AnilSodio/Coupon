/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.web.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinco.common.utils.CookieUtils;
import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.se.FeedbackMessageService;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.web.i18n.MessageResource;
import vc.thinker.core.web.BaseController;
import vc.thinker.core.web.ValidateCodeHandler;
import vc.thinker.pay.PayChannel;
import vc.thinker.pay.PayHandlerFactory;
import vc.thinker.pay.alipay.AlipayPayHandler;
import vc.thinker.pay.alipay.AlipayPayNotify;
import vc.thinker.sys.model.User;
import vc.thinker.web.utils.UserUtils;

/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController{
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private PayHandlerFactory handlerFactory;
	
	@Autowired
	private ValidateCodeHandler validateCodeHandler;
	
	@Autowired
	private FeedbackMessageService feedbackMessageService;
	
//	@Autowired
//    private MessageSource messageSource;
//	
//	@Autowired
//    private ResourceDao resourceDao;

	@Autowired
    private MessageResource messageResource;
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			return "redirect:"+adminPath;
		}
		return "/login";
	}

    /**
     * 登录失败，真正登录的POST请求由Filter完成
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 如果已经登录，则跳转到管理首页
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:" + adminPath;
        }
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute("isValidateCodeLogin", validateCodeHandler.isValidateCode(username));
        return "/login";
    }


	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresUser
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
		User user = UserUtils.getUser();
		// 未登录，则跳转到登录页
		if(user.getId() == null){
			return "redirect:"+adminPath+"/login";
		}
		//messageResource.reload();
		//messageSource.r
//		MessageResource re = new MessageResource(resourceDao);
		Locale locale = LocaleContextHolder.getLocale();
		System.out.println(messageResource.getMessage("common.name", null, locale));
//		SysPrincipal principal=UserUtils.getPrincipal();
		
		// 登录成功后，获取上次登录的当前站点ID
//		UserUtils.putCache("siteId", CookieUtils.getCookie(request, "siteId"));

        SysSetting sysSettingBO = sysSettingDao.findOne();

        model.addAttribute("isOpenMemberCard", sysSettingBO.getIsOpenMemberCard());
        model.addAttribute("isOpenBalance", sysSettingBO.getIsOpenBalance());
        model.addAttribute("sysSettingBO", sysSettingBO);

//		if(feedbackMessageService.countByUnRead()>0){
//			model.addAttribute("haveNewMsg", true);
//		}else {
//			model.addAttribute("haveNewMsg", false);
//		}
        return "modules/index";
    }

    /**
     * 获取主题方案
     */
    @RequestMapping(value = "/theme/{theme}")
    public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(theme)) {
            CookieUtils.setCookie(response, "theme", theme);
        } else {
            theme = CookieUtils.getCookie(request, "theme");
        }
        return "redirect:" + request.getParameter("url");
    }

    @RequiresGuest
    @RequestMapping(value = "/pay/return")
    public @ResponseBody
    String payReturn(HttpServletRequest request, HttpServletResponse response) {

        AlipayPayHandler payHandler = handlerFactory.getPayHandler(PayChannel.ALIPAY);

        AlipayPayNotify payNotify = payHandler.getPayNotify(request);
        System.out.println(payNotify.getReqAttach());
        if (payHandler.verifyNotify(payNotify)) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @SuppressWarnings("resource")
    @RequestMapping("${adminPath}/download")
    public String download(@RequestParam String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            response.reset();
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            OutputStream outputStream = new BufferedOutputStream(
                    response.getOutputStream());
            byte data[] = new byte[1024];
            while (inputStream.read(data, 0, 1024) >= 0) {
                outputStream.write(data);
            }
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

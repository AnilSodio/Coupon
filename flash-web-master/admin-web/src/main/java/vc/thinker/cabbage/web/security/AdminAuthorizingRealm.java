/**
* Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.web.security;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.Encodes;

import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.service.AdminService;
import vc.thinker.core.web.LoginCommon;
import vc.thinker.core.web.ValidateCodeHandler;
import vc.thinker.core.web.security.CaptchaException;
import vc.thinker.core.web.security.SessionAuthorizingRealm;
import vc.thinker.core.web.security.UsernamePasswordToken;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;

/**
 * 系统安全认证实现类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class AdminAuthorizingRealm extends SessionAuthorizingRealm {

	@Autowired
	@Lazy(true) //类里的所有service都必须懒加载，不然service会没有事务
	private SystemService systemService;
	
	@Autowired
	@Lazy(true)
	private UserAccountService accountService;
	
	@Autowired
	@Lazy(true)
	private AdminService adminService;
	
	private Boolean isIpCheck;
	
	private ValidateCodeHandler validateCodeHandler;
	
	private final Logger log=LoggerFactory.getLogger(this.getClass());
	

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		if (validateCodeHandler!= null && validateCodeHandler.isValidateCode(token.getUsername())){
			// 判断验证码
			Session session = SecurityUtils.getSubject().getSession();
			String code = (String)session.getAttribute(LoginCommon.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
				throw new CaptchaException("Verification code error");
			}
		}

		UserAccountBO account = accountService.findByLoginName(token.getUsername());
		if(account == null){
			throw new UnknownAccountException("User name not exit");
		}
		UserBO user=systemService.getUser(account.getUid());
		if(user == null){
			throw new UnknownAccountException("User name not exit");
		}
		AdminBO admin=adminService.findById(user.getId());
		if(admin == null || admin.getIsDel()){
			throw new UnknownAccountException("User name not exit");
		}
		
		byte[] salt = Encodes.decodeHex(account.getPassword().substring(0,16));
		return new SimpleAuthenticationInfo(new AdminPrincipal(user,admin,account.getLoginName(),token.getHost()), 
				account.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		AdminPrincipal principal = (AdminPrincipal) getAvailablePrincipal(principals);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String host=principal.getHost();
		
		List<PermissionBO> perms=systemService.findPermByUid(principal.getUser(), SysUserContant.USER_TYPE_1);
		
//		if(isIpCheck ){
//			list= UserUtils.getMenuList(host);
//		}else{
//			list= UserUtils.getMenuList();
//		}
		for (PermissionBO perm : perms){
			// 添加基于Permission的权限信息
//			for (String permission : StringUtils.split(menu.getPermission(),",")){
			if(StringUtils.isNotBlank(perm.getValue())){
				info.addStringPermission(perm.getValue());
			}
//			}
		}
		return info;
	}
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(PasswordUtil.HASH_ALGORITHM);
		matcher.setHashIterations(PasswordUtil.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		return systemService;
	}
	
	
	public UserAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(UserAccountService accountService) {
		this.accountService = accountService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public Boolean getIsIpCheck() {
		return isIpCheck;
	}

	public void setIsIpCheck(Boolean isIpCheck) {
		this.isIpCheck = isIpCheck;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public ValidateCodeHandler getValidateCodeHandler() {
		return validateCodeHandler;
	}

	public void setValidateCodeHandler(ValidateCodeHandler validateCodeHandler) {
		this.validateCodeHandler = validateCodeHandler;
	}
}

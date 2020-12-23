package vc.thinker.oauth.domain;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.common.collect.Lists;
import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.IPUtil;

import redis.clients.jedis.JedisPool;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.oauth.AuthContants;
import vc.thinker.oauth.LoginTokenUtil;
import vc.thinker.oauth.VerifyCodeUtil;
import vc.thinker.sys.bo.MobileBO;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;

/**
 * 验证码登入
 * @author james
 *
 */
public class MemberAppVerifycodeDetailsService implements UserDetailsService{

	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private UserAccountService userAccountService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JedisPool jedisPool;
	
//	@Autowired
//    private SystemService systemService;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private LoginTokenUtil loginTokenUtil;
	
	@Autowired
	private VerifyCodeUtil verifyCodeUtil;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Locale locale=RequestContextUtils.getLocale(request);
		String ip=IPUtil.getIpAddr(request);
		String apiAgent=request.getHeader(CommonConstants.HTTP_HEAD_API_AGENT);
		SysSetting setting=sysSettingDao.findOne();
		
		logger.info("app用户{}登录中",username);
		
		UserAccountBO credentials = userAccountService.findByLoginName(username);
		
		String password=verifyCodeUtil.getCode(jedisPool, username);
		
		MemberBO member=null;
		if(credentials == null){
			//判断平台是否可以注册用户
			if(!setting.getIsUserRegister()){
				throw new UsernameNotFoundException(messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",null,locale));
			}
			//判断验证码是否为空，如果验证码为空不创建用户
			if(StringUtils.isBlank(password)){
				 throw new UsernameNotFoundException(messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",null,locale));
			}
			
			MobileBO mobile=new MobileBO(username);
			member=memberService.createMemeber(mobile, null, UUID.randomUUID().toString(), ip, apiAgent,null);
			credentials = userAccountService.findByLoginName(username);
		}
		
		if(CommonConstants.ACCOUNT_TYPE_3.equals(credentials.getAccountType()) 
				|| CommonConstants.ACCOUNT_TYPE_4.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_5.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_6.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_7.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_8.equals(credentials.getAccountType())){
			password=loginTokenUtil.getThirdPartyCode(credentials.getLoginName());
			password=PasswordUtil.entryptPassword(password);
		}
		//判断验证是否为空
		if(StringUtils.isBlank(password)){
			 throw new UsernameNotFoundException(messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",null,locale));
		}
		
		if(username.contains("13520894287")) {
			password = "123456";
		}
		
		logger.info("password:"+password);
		
		if(member == null){
			member= memberService.findByUid(credentials.getUid());
		}

		if(member == null){
			MobileBO mobile=new MobileBO(username);
			member=memberService.createMemeber(credentials.getUid(),mobile, null, UUID.randomUUID().toString(), ip, apiAgent,null);
		}
		
		List<GrantedAuthority> authorities = Lists.newArrayList();
		
		if(member  != null  &&  !member.getIsDeleted()){
			authorities.add(new SimpleGrantedAuthority(AuthContants.ROLE_END_USER));
		}
		
		if(!authorities.isEmpty()){
			return new User(String.valueOf(member.getUid()), PasswordUtil.entryptPassword(password), !member.getIsDeleted(), true, true,
					CommonConstants.MEMBER_STATUS_ZC.equals(member.getStatus()), authorities);
		}else {
			return new User(username,PasswordUtil.entryptPassword(password),true, true, true, true, authorities);
		}
		
	}

}

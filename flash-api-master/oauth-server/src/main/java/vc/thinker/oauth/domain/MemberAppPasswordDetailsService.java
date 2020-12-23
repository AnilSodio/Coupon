package vc.thinker.oauth.domain;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
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

import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.oauth.AuthContants;
import vc.thinker.oauth.LoginTokenUtil;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.service.UserAccountService;

/**
 * 密码登入
 * @author james
 *
 */
public class MemberAppPasswordDetailsService implements UserDetailsService{

	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private UserAccountService userAccountService;
	
	@Autowired
	private MemberService memberService;
	
//	@Autowired
//	private JedisPool jedisPool;
	
//	@Autowired
//    private SystemService systemService;
	
//	@Autowired
//	private SysSettingDao sysSettingDao;
	
	@Autowired
	private LoginTokenUtil loginTokenUtil;
	
//	@Autowired
//	private VerifyCodeUtil verifyCodeUtil;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Locale locale=RequestContextUtils.getLocale(request);
		
		logger.info("app用户{}登录中",username);
		logger.info("local为{}",locale.getLanguage()); 
		UserAccountBO credentials = userAccountService.findByLoginName(username);
		
		if(credentials == null){
			String message = messageSource.getMessage("DigestAuthenticationFilter.userRegistered", null, locale);
			logger.info("message:",message);
			throw new UsernameNotFoundException(message);
		}
		MemberBO member= memberService.findByUid(credentials.getUid());

		List<GrantedAuthority> authorities = Lists.newArrayList();
		authorities.add(new SimpleGrantedAuthority(AuthContants.ROLE_END_USER));
		
		String password=credentials.getPassword();
		if(CommonConstants.ACCOUNT_TYPE_3.equals(credentials.getAccountType()) 
				|| CommonConstants.ACCOUNT_TYPE_4.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_5.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_6.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_7.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_8.equals(credentials.getAccountType())
				|| CommonConstants.ACCOUNT_TYPE_10.equals(credentials.getAccountType())
				){
			password=loginTokenUtil.getThirdPartyCode(credentials.getLoginName());
			password=PasswordUtil.entryptPassword(password);
		}
		
		logger.info("password:"+password);
		
		return new User(String.valueOf(member.getUid()), password, !member.getIsDeleted(), true, true,
				CommonConstants.MEMBER_STATUS_ZC.equals(member.getStatus()), authorities);
	}
 
}

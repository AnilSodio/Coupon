package vc.thinker.oauth.domain;

import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Lists;
import com.sinco.common.area.CountryUtil;
import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.IPUtil;

import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.service.UpdateUserStatsService.UserRegistEvent;
import vc.thinker.oauth.AuthContants;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.Jscode2sessionResult;


public class MemberWxAppletUserDetailsService implements UserDetailsService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private UserAccountService userAccountService;
	
	@Autowired
    private SystemService systemService;
	
	@Autowired
    private MemberDao memberDao;
	
//	@Autowired
//    private UniqueRadomCodeService uniqueRadomCodeService;
	
	@Value("${wx.appid}")
	private String appId;
	
	@Value("${wx.secret}")
	private String secret;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ip=IPUtil.getIpAddr(request);
		String apiAgent=request.getHeader(CommonConstants.HTTP_HEAD_API_AGENT);
        logger.info("个人用户{}登录中",username);
        Jscode2sessionResult result = SnsAPI.jscode2session(appId, secret, username);
        logger.info("微信授权结果：【"+ result.getErrcode()+"】【"+ result.getErrmsg()+"】");
       
        if(result!=null){
        	String openId = result.getOpenid();
        	UserAccountBO credentials = userAccountService.findByLoginName(openId);
        	String password =PasswordUtil.entryptPassword("123456");
        	List<GrantedAuthority> authorities = Lists.newArrayList();
        	Long uid =null;
        	if(credentials == null){
        		uid = systemService.createUser(CommonConstants.ACCOUNT_TYPE_3, openId, "123456",ip,CountryUtil.get("86"),null);
        	}else{
        		uid=credentials.getUid();
        	}
        	MemberBO member= memberDao.findOne(uid);
        	if(member == null){
        		member = new MemberBO();
        		member.setUid(uid);
        		member.setCreateTime(new Date());
        		member.setStatus(CommonConstants.MEMBER_STATUS_ZC);
        		member.setAuthStatus(CommonConstants.MEMBER_AUTH_STATUS_YET);
        		member.setAuthStep(CommonConstants.MEMBER_AUTH_STEP_1);
        		member.setRegisteredIp(ip);
        		member.setClientSource(apiAgent);
        		member.setIsDeleted(false);
        		member.setCountry("86");
        		member.setCurrency("CNY");
        		memberDao.save(member);
        		
        		UserRegistEvent userRegistEvent=new UserRegistEvent();
        		userRegistEvent.setUid(uid);
        		publisher.publishEvent(userRegistEvent);
        		
        	}
        	if(member  != null && member.getStatus().equals(CommonConstants.MEMBER_STATUS_ZC)){
				authorities.add(new SimpleGrantedAuthority(AuthContants.ROLE_END_USER));
			}
			if(!authorities.isEmpty()){
				return new User(String.valueOf(uid), password, !member.getIsDeleted(), true, true, true, authorities);
			}
        }
        throw new UsernameNotFoundException("登录失败");
    }
}

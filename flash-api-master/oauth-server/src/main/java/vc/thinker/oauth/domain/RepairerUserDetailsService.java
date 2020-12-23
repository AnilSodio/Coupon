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

import redis.clients.jedis.JedisPool;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.user.bo.RepairerBO;
import vc.thinker.cabbage.user.dao.RepairerDao;
import vc.thinker.oauth.AuthContants;
import vc.thinker.oauth.VerifyCodeUtil;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.service.UserAccountService;

/**
 * 维保人员处理
 * @author james
 *
 */
public class RepairerUserDetailsService implements UserDetailsService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private UserAccountService userAccountService;
	
	@Autowired
	private RepairerDao repairerDao;
	
	@Autowired
	protected MessageSource messageSource;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("维保人员{}登录中",username);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Locale locale=RequestContextUtils.getLocale(request);
        UserAccountBO credentials = userAccountService.findByLoginName(username);//.findByName(username);

    	if(credentials != null){
    		RepairerBO repairer=repairerDao.findOne(credentials.getUid());
    		
    		
    		List<GrantedAuthority> authorities = Lists.newArrayList();
    		if(repairer  == null ){
    			throw new UsernameNotFoundException("您不是维保人员，请连接后台管理员添加");
    		}
    		
    		String password=credentials.getPassword();
    		
    		authorities.add(new SimpleGrantedAuthority(AuthContants.ROLE_REPAIRER_USER));
    		
			return new User(String.valueOf(repairer.getUid()), password,true, true, true, repairer.getStatus().equals(SeCommonConstants.USER_REPAIRER_STATUS_1), authorities);
    	}
    	throw new UsernameNotFoundException(messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",null,locale));
    }
}

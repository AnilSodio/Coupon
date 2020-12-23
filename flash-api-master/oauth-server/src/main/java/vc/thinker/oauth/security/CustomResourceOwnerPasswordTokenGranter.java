package vc.thinker.oauth.security;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import redis.clients.jedis.JedisPool;
import vc.thinker.cabbage.user.service.UserAppMessageService.LogoutEvent;
import vc.thinker.oauth.VerifyCodeUtil;

public class CustomResourceOwnerPasswordTokenGranter extends AbstractTokenGranter {

	private static final Logger log=LoggerFactory.getLogger(CustomResourceOwnerPasswordTokenGranter.class);
	
	private static final String GRANT_TYPE = "password";

	private final AuthenticationManager authenticationManager;

	public final static String AUTHORITY_STORE_CS_CLIENT ="STORE_CS_CLIENT";
	
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private VerifyCodeUtil verifyCodeUtil;
	
	private JedisPool jedisPool;

	public CustomResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory) {
		this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
	}

	protected CustomResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory, String grantType) {
		super(tokenServices, clientDetailsService, requestFactory, grantType);
		this.authenticationManager = authenticationManager;
	}

	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
		String username = parameters.get("username");
		String password = parameters.get("password");
		// Protect from downstream leaks of password
		parameters.remove("password");
		Authentication userAuth = null;
		if(client.getAuthorities().contains(new SimpleGrantedAuthority(ClientAuthoritie.MEMBER_APP_CLIENT.name()))){
			userAuth = new MemberAppUsernamePasswordAuthenticationToken(username, password);
		}else if(client.getAuthorities().contains(new SimpleGrantedAuthority(ClientAuthoritie.MEMBER_WX_APPLET_CLIENT.name()))){
			userAuth = new MemberWxAppletUsernamePasswordAuthenticationToken(username, password);
		}else if(client.getAuthorities().contains(new SimpleGrantedAuthority(ClientAuthoritie.REPAIRER_CLIENT.name()))){
			userAuth = new RepairerUsernamePasswordAuthenticationToken(username, password);
		}else {
			userAuth = new MemberAppUsernamePasswordAuthenticationToken(username, password);
		}
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
		try {
			userAuth = authenticationManager.authenticate(userAuth);
		} catch (AccountStatusException ase) {
			// covers expired, locked, disabled cases (mentioned in section 5.2,
			// draft 31)
			throw new InvalidGrantException(ase.getMessage());
		} catch (BadCredentialsException e) {
			// If the username/password are wrong the spec says we should send
			// 400/invlid grant
			throw new BadCredentialsException(e.getMessage());
		} catch (InternalAuthenticationServiceException e) {
			log.error("授权业务处理失败",e);
			throw e;
		}
		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + username);
		}
		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
		OAuth2Authentication oauth2 =  new OAuth2Authentication(storedOAuth2Request, userAuth);
		DefaultTokenServices tokenservices = (DefaultTokenServices) this.getTokenServices();
		OAuth2AccessToken access = tokenservices.getAccessToken(oauth2);
		if(access != null){
			log.info("清除用户[{}] 的token:[{}]",username,access.getValue());
			LogoutEvent logoutEvent=new LogoutEvent();
			logoutEvent.setAccessToken(access.getValue().replaceAll("\\-", ""));
			logoutEvent.setContent("Your account at "+new Date()+" logged in on other devices, if this is not your operation, please contact the administrator.");
			tokenservices.revokeToken(access.getValue());
			if(client.getAuthorities().contains(new SimpleGrantedAuthority(ClientAuthoritie.MEMBER_APP_CLIENT.name()))){
				logoutEvent.setToMember(true);
			}
			publisher.publishEvent(logoutEvent);
		}
		
		verifyCodeUtil.delCode(jedisPool, userAuth.getName());
		log.info("用户 {} 登陆成功，清除验证码",username);
		
		return oauth2;
	}

	public class MemberAppUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

		public MemberAppUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
			super(principal, credentials);
		}

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

	}
	
	public class MemberWxAppletUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken{

		public MemberWxAppletUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
			super(principal, credentials);
		}
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	
	public class RepairerUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
		
		public RepairerUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
			super(principal, credentials);
		}
		
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public VerifyCodeUtil getVerifyCodeUtil() {
		return verifyCodeUtil;
	}

	public void setVerifyCodeUtil(VerifyCodeUtil verifyCodeUtil) {
		this.verifyCodeUtil = verifyCodeUtil;
	}

	public void setPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
}

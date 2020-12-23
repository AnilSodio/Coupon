package vc.thinker.cabbage.web;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.google.common.collect.Lists;

import redis.clients.jedis.JedisPool;
import vc.thinker.cabbage.web.security.RefereeAuthorizingRealm;
import vc.thinker.cabbage.web.filter.EnSysFormAuthenticationFilter;
import vc.thinker.cabbage.web.security.LogoutSessionListener;
import vc.thinker.core.web.security.CountValidateCodeHandler;
import vc.thinker.core.web.security.KickoutSessionControlFilter;
import vc.thinker.web.security.SysFormAuthenticationFilter;

/**
 * shrio 配制类
 * @author james
 *
 */
@Configuration
@ConfigurationProperties
@EnableRedisHttpSession
public class ShiroConfig {
	
	@Value("${auth.check.ip}")
	private Boolean authCheckIp;
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	@Resource(name="cabbageRedisPool")
	private JedisPool cabbageRedisPool;
	
	@Bean  
    public RedisTemplate<String, Deque<String>> dequeRedisTemplate(  
            RedisConnectionFactory factory) {  
        RedisTemplate<String, Deque<String>> redisTemplate = new RedisTemplate<String, Deque<String>>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
        return redisTemplate;  
    }  
	
	@Bean(name = "securityManager")
	public org.apache.shiro.mgt.SecurityManager securityManager(RefereeAuthorizingRealm systemAuthorizingRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(systemAuthorizingRealm);
		return securityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager,FormAuthenticationFilter formAuthenticationFilter) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl(adminPath);
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("authc", formAuthenticationFilter);
		filters.put("kickoutFilter", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filters);

		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
		filterChainDefinitionManager.put("/static/**", "anon");
		filterChainDefinitionManager.put("/userfiles/**", "anon");
		filterChainDefinitionManager.put(adminPath+"/logout", "logout");
		filterChainDefinitionManager.put(adminPath+"/**", "kickoutFilter,user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

		return shiroFilterFactoryBean;
	}
	
	@Bean
	public RefereeAuthorizingRealm systemAuthorizingRealm(CountValidateCodeHandler validateCodeHandler){
		RefereeAuthorizingRealm adminAuthorizingRealm=new RefereeAuthorizingRealm();
		adminAuthorizingRealm.setIsIpCheck(authCheckIp);
		adminAuthorizingRealm.setValidateCodeHandler(validateCodeHandler);
		return adminAuthorizingRealm;
	}
	
	@Bean
	@Lazy(true)
	public CountValidateCodeHandler validateCodeHandler(){
		CountValidateCodeHandler validateCodeHandler=new CountValidateCodeHandler();
		validateCodeHandler.setFailCount(3);
		validateCodeHandler.setPool(cabbageRedisPool);
		return validateCodeHandler;
	}
	
	@Bean
	public FormAuthenticationFilter formAuthenticationFilter(CountValidateCodeHandler validateCodeHandler){
		EnSysFormAuthenticationFilter formAuthenticationFilter=new EnSysFormAuthenticationFilter();
		//<!-- 最大session 数 -->
		formAuthenticationFilter.setMaxSession(1);
		//<!-- false 踢出之前登录的/ true 之后登录的用户 -->
		formAuthenticationFilter.setKickoutAfter(false);
		//<!-- 踢出后url -->
		formAuthenticationFilter.setKickoutUrl("/login");
		//<!-- 配制限制session数所需，spring sesison  repository-->
		formAuthenticationFilter.setValidateCodeHandler(validateCodeHandler);
		return formAuthenticationFilter;
	}
	
	/**
	 * 限制session数过滤器
	 * @return
	 */
	@Bean
	public KickoutSessionControlFilter kickoutSessionControlFilter(){
		return new KickoutSessionControlFilter();
	}
	
	/**
	 * logout 监听
	 * @return
	 */
	@Bean
	public LogoutSessionListener logoutSessionListener(){
		return new LogoutSessionListener();
	}
	
	@Bean
	public SessionManager sessionManager(LogoutSessionListener logoutSessionListener){
		DefaultWebSessionManager webSessionManager=new DefaultWebSessionManager();
		webSessionManager.setSessionListeners(Lists.newArrayList(logoutSessionListener));
		return webSessionManager;
	}
	
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}

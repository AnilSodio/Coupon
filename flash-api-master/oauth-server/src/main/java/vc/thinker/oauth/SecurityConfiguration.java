package vc.thinker.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import vc.thinker.oauth.domain.MemberAppPasswordDetailsService;
import vc.thinker.oauth.domain.MemberWxAppletUserDetailsService;
import vc.thinker.oauth.domain.RepairerUserDetailsService;
import vc.thinker.oauth.security.MemberAppAuthenticationProvider;
import vc.thinker.oauth.security.MemberWxAppletAuthenticationProvider;
import vc.thinker.oauth.security.RepairerAuthenticationProvider;
import vc.thinker.oauth.security.ShiroPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
    protected UserDetailsService memberWxAppletUserDetailsService() {
        return new MemberWxAppletUserDetailsService();
    }
	
	@Bean
	protected UserDetailsService memberAppDetailsService() {
		return new MemberAppPasswordDetailsService();
	}
	
	@Bean
	protected RepairerUserDetailsService repairerUserDetailsService() {
		return new RepairerUserDetailsService();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new ShiroPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**","/css/**","/js/**","/fonts/**"
        		,"/images/**","/vendors/**","/health");

    }


    @Bean
    public MemberWxAppletAuthenticationProvider memberWxAppletAuthenticationProvider(@Qualifier("memberWxAppletUserDetailsService") UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
    	MemberWxAppletAuthenticationProvider provider = new MemberWxAppletAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	provider.setHideUserNotFoundExceptions(false);
    	return provider;
    }
    
    @Bean
    public MemberAppAuthenticationProvider memberAppAuthenticationProvider(@Qualifier("memberAppDetailsService") UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
    	MemberAppAuthenticationProvider provider = new MemberAppAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	provider.setHideUserNotFoundExceptions(false);
    	return provider;
    }
    
    @Bean
    public RepairerAuthenticationProvider repairerAuthenticationProvider(@Qualifier("repairerUserDetailsService") UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
    	RepairerAuthenticationProvider provider = new RepairerAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	provider.setHideUserNotFoundExceptions(false);
    	return provider;
    }

    /**
     * 注意顺序，默认的normalUserAuthenticationProvider 必须在最后面
     */
    @Bean
    public AuthenticationManager authenticationManager(MemberWxAppletAuthenticationProvider memberWxAppletAuthenticationProvider,
    		MemberAppAuthenticationProvider memberAppAuthenticationProvider,RepairerAuthenticationProvider repairerAuthenticationProvider) {
        return new ProviderManager(Arrays.asList(memberWxAppletAuthenticationProvider,memberAppAuthenticationProvider,repairerAuthenticationProvider));
    }
    /*
	@Override
    @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}*/

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/logout.do").permitAll()
                .antMatchers("/**").authenticated()
            .and()
            .formLogin()
                .loginProcessingUrl("/login.do")
                .usernameParameter("name")
                .loginPage("/login")
            .and()
            .logout()
                //To match GET requests we have to use a request matcher.
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"));
            //.and()
            //.userDetailsService(userDetailsService());
        http.sessionManagement().maximumSessions(1);
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}

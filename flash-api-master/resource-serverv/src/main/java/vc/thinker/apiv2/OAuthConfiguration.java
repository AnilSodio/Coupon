package vc.thinker.apiv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableResourceServer
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {


    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Bean
    public TokenStore tokenStore(){
    	return new RedisTokenStore(redisConnectionFactory);
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("todo-services")
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**").and()
            // For some reason we cant just "permitAll" OPTIONS requests which are needed for CORS support. Spring Security
            // will respond with an HTTP 401 nonetheless.
            // So we just put all other requests types under OAuth control and exclude OPTIONS.
            .authorizeRequests()
            //排除权限
            	.antMatchers(new String[]{"/api/device/**","/**/favicon.ico"}).permitAll()
            //权限
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/api/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("#oauth2.hasScope('write')")
        .and()
            // Add headers required for CORS requests.
            .headers().addHeaderWriter((request, response) -> {
                response.addHeader("Access-Control-Allow-Origin", "*");
                if (request.getMethod().equals("OPTIONS")) {
                    response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                }
            });
    }

}

package vc.thinker.oauth;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import redis.clients.jedis.JedisPool;
import vc.thinker.oauth.security.CustomResourceOwnerPasswordTokenGranter;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;
@EnableTransactionManagement(proxyTargetClass = true)

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {


	@Autowired DataSource dataSource;
   /* @Bean
    @ConfigurationProperties(prefix = "spring.datasource_oauth")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }
*/
    @Autowired
    //@Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;
	
	@Autowired
	private UserAccountService userAccountService;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    
    @Autowired
    private SystemService systemService;
    
    @Autowired
	private JedisPool jedisPool;
    
    @Autowired
    private VerifyCodeUtil verifyCodeUtil;
    
	@Autowired
	private ApplicationEventPublisher publisher;
    
    /**
     * We expose the JdbcClientDetailsService because it has extra methods that the Interface does not have. E.g.
     * {@link org.springframework.security.oauth2.provider.client.JdbcClientDetailsService#listClientDetails()} which we need for the
     * admin page.
     */
    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
    	  return new RedisTokenStore(redisConnectionFactory);//new JdbcTokenStore(oauthDataSource());
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();
    }


    public TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {

        ClientDetailsService clientDetails = clientDetailsService();
        AuthorizationServerTokenServices tokenServices = tokenServices();
        AuthorizationCodeServices authorizationCodeServices = authorizationCodeServices();
        OAuth2RequestFactory requestFactory =endpoints.getOAuth2RequestFactory();

        List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();

        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices,
                clientDetails, requestFactory));
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory));
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
        /*tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,
                    clientDetails, requestFactory));*/
        
        CustomResourceOwnerPasswordTokenGranter tokenGranter= new CustomResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetails,
                requestFactory);
        tokenGranter.setJedisPool(jedisPool);
        tokenGranter.setVerifyCodeUtil(verifyCodeUtil);
        tokenGranter.setPublisher(publisher);
        tokenGranters.add(tokenGranter);

        return new CompositeTokenGranter(tokenGranters);
}
    @Bean

    public AuthorizationServerTokenServices tokenServices()  {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService());
        //tokenServices.setTokenEnhancer(osiamTokenEnhancer());
        try {
			tokenServices.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .setClientDetailsService(clientDetailsService());;
        		endpoints.tokenGranter(tokenGranter(endpoints));

    }

}

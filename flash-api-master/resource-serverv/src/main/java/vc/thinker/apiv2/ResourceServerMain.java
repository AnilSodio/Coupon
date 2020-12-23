package vc.thinker.apiv2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestHeader;

import com.google.common.base.Predicate;
import com.sinco.common.word.SensitivewordFilter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vc.thinker.cabbage.core.config.DatabaseConfig;
import vc.thinker.cabbage.core.config.DicConfig;
import vc.thinker.lbs.BaiduLbsBiz;
import vc.thinker.lbs.LbsBiz;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;
import vc.thinker.pay.CabbagePayConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "vc.thinker.**" })
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@ImportAutoConfiguration(value={DatabaseConfig.class,DicConfig.class,CabbagePayConfig.class})
public class ResourceServerMain {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerMain.class, args);
	}
	
	@Value("${api.version}")
	private String version;
	
	@Value("${lbs.baidu.ak}")
	private String baiduAk;
	
	@Value("${iot.paas.id}")
	private String paasId;
	
	@Value("${iot.paas.url}")
	private String paasUrl;
	
	@Bean(name="relinkSDK")
	public RelinkOpenSDK relinkOpenSDK(){
		return new RelinkOpenSDK(paasId, paasUrl);
	}
	

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("radish-api")
				.select()
				.paths(paths())
				// and by paths
				.build()
				.forCodeGeneration(true)
				.ignoredParameterTypes(Principal.class, User.class,OAuth2Authentication.class
						,RequestHeader.class,HttpServletResponse.class)
				.apiInfo(apiInfo())
				.securitySchemes(
						Arrays.asList(securityClientCredentialsSchema(),
								securityPasswordSchema()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	public static final String securitySchemaOAuth2Cc = "oauth2-clientcredentials";
	public static final String securitySchemaOAuth2Pwd = "oauth2-password";
	public static final String authorizationScopeRead = "read";
	public static final String authorizationScopeWirte = "write";

	private OAuth securityClientCredentialsSchema() {
		AuthorizationScope authorizationScope = new AuthorizationScope(
				authorizationScopeRead, authorizationScopeWirte);
		GrantType grantType = new ClientCredentialsGrant(
				"http://auth.thinker.vc/oauth/token");
		return new OAuth(securitySchemaOAuth2Cc,
				Arrays.asList(authorizationScope), Arrays.asList(grantType));
	}

	private OAuth securityPasswordSchema() {
		AuthorizationScope authorizationScope = new AuthorizationScope(
				authorizationScopeRead, authorizationScopeWirte);

		GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(
				"http://auth.thinker.vc/oauth/token");
		return new OAuth(securitySchemaOAuth2Pwd,
				Arrays.asList(authorizationScope), Arrays.asList(grantType));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(paths()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope(
				authorizationScopeRead, authorizationScopeWirte);
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference(
				securitySchemaOAuth2Pwd, authorizationScopes));
	}

	private Predicate<String> paths() {
		return regex("/api/*.*");
	}
	
	@Bean
	public LbsBiz lbsBiz(){
		return new BaiduLbsBiz(baiduAk);
	}

	@Bean(name = "dozerBean")
    public DozerBeanMapperFactoryBean configDozer() throws IOException {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:/dozer/*Mapping.xml");
        mapper.setMappingFiles(resources);
        return mapper;
    }
	
	@Bean
	public SensitivewordFilter sensitivewordFilter(){
		Resource resource=new PathMatchingResourcePatternResolver().getResource("/sensitive-words.dic");
		try {
			return new SensitivewordFilter(resource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("新科-印度API").description("Android、iOS操作类")
				.termsOfServiceUrl("http://www.thinker.vc").contact("新科聚合")
				.version(version).build();
	}

}

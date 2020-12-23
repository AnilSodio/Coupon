package vc.thinker.cabbage.web;

import java.io.IOException;
import java.util.Locale;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;

import vc.thinker.cabbage.core.config.DatabaseConfig;
import vc.thinker.cabbage.core.config.DicConfig;
import vc.thinker.cabbage.web.i18n.MessageResource;
import vc.thinker.cabbage.web.i18n.MyLocaleResolver;
import vc.thinker.lbs.BaiduLbsBiz;
import vc.thinker.lbs.LbsBiz;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;
import vc.thinker.pay.CabbagePayConfig;
import vc.thinker.sys.service.SecurityInitHandle;
import vc.thinker.sys.service.SystemService;
import vc.thinker.utils.SpringContextHolder;

@SpringBootApplication
@ComponentScan(basePackages = { "vc.thinker.**" },
excludeFilters={@Filter(type=FilterType.REGEX,pattern={"vc\\.thinker\\.sys\\.web\\.action\\..+"})})
@EnableAutoConfiguration(exclude={FreeMarkerAutoConfiguration.class
		,OAuth2AutoConfiguration.class,SecurityAutoConfiguration.class
		,SpringBootWebSecurityConfiguration.class
		,ManagementWebSecurityAutoConfiguration.class})
@EnableRedisHttpSession
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@ImportAutoConfiguration(value={DatabaseConfig.class,DicConfig.class,CabbagePayConfig.class})
public class SellerServerMain {
	
	public static void main(String[] args) {
		SpringApplication.run(SellerServerMain.class, args);
	}
	
	@Autowired
	private MessageResource messageResource;
	
	@Autowired
	private SystemService systemService;
	
	@Value("${lbs.baidu.ak}")
	private String baiduAK;
	
	@Value("${iot.paas.id}")
	private String paasId;
	
	@Value("${iot.paas.url}")
	private String paasUrl;
	
	@Bean(name="relinkSDK")
	public RelinkOpenSDK relinkOpenSDK(){
		return new RelinkOpenSDK(paasId, paasUrl);
	}
	
	@Bean
	public LbsBiz lbsBiz(){
		return new BaiduLbsBiz(baiduAK);
	}
	
	@Bean
	@Lazy(false)
	public vc.thinker.utils.SpringContextHolder springContextHolder(){
		return new SpringContextHolder();
	}
	
	@Bean
	public LocalValidatorFactoryBean validator(){
		return new LocalValidatorFactoryBean();
	}
	
	@Bean(initMethod="init")
	@Lazy(false)
	public SecurityInitHandle securityInitHandle(){
		SecurityInitHandle securityInitHandle=new SecurityInitHandle();
		securityInitHandle.setPack("vc.thinker.*.web");
		securityInitHandle.setRunInit(false);
		securityInitHandle.setUserType(1);
		securityInitHandle.setSystemService(systemService);
		
		// 开启国际化
		securityInitHandle.setEnableMessageSource(true);
		securityInitHandle.setMessageSource(messageResource);
		securityInitHandle.setLocale(Locale.ENGLISH);
		return securityInitHandle;
	}

	@Bean(name = "dozerBean")
    public DozerBeanMapperFactoryBean configDozer() throws IOException {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:/dozer/*Mapping.xml");
        mapper.setMappingFiles(resources);
        return mapper;
    }
	
	@Bean  
    public LocaleResolver localeResolver() {  
        return new MyLocaleResolver();  
    } 
	
	@Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
		JettyEmbeddedServletContainerFactory factory=new JettyEmbeddedServletContainerFactory();
		//配合spring boot 1.3 配制 max http post size
		customizeMaxHttpPostSize(factory, 200000);
		return factory;
	}
	//配合spring boot 1.3 配制 max http post size
	private void customizeMaxHttpPostSize(
			JettyEmbeddedServletContainerFactory factory, final int maxHttpPostSize) {
		factory.addServerCustomizers(new JettyServerCustomizer() {

			@Override
			public void customize(Server server) {
				setHandlerMaxHttpPostSize(maxHttpPostSize, server.getHandlers());
			}

			private void setHandlerMaxHttpPostSize(int maxHttpPostSize,
					Handler... handlers) {
				for (Handler handler : handlers) {
					if (handler instanceof ContextHandler) {
						((ContextHandler) handler)
								.setMaxFormContentSize(maxHttpPostSize);
					}
					else if (handler instanceof HandlerWrapper) {
						setHandlerMaxHttpPostSize(maxHttpPostSize,
								((HandlerWrapper) handler).getHandler());
					}
					else if (handler instanceof HandlerCollection) {
						setHandlerMaxHttpPostSize(maxHttpPostSize,
								((HandlerCollection) handler).getHandlers());
					}
				}
			}

		});
	}
}

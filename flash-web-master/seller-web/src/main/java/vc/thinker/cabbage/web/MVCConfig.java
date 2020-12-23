package vc.thinker.cabbage.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.TemplateException;
import vc.thinker.cabbage.web.i18n.MessageResource;
import vc.thinker.core.web.CabbageMappingExceptionResolver;
import vc.thinker.core.web.SecureModelAttributeMethodArgumentResolver;
import vc.thinker.core.web.view.MyFreeMarkerConfigurer;
import vc.thinker.sys.dao.LogDao;
import vc.thinker.web.interceptor.LogInterceptor;

/**
 * MVC 配制
 * 
 * @author james
 *
 */
@Configuration
@ConfigurationProperties
public class MVCConfig extends WebMvcConfigurerAdapter {

	@Value("${adminPath}")
	private String adminPath;

	@Value("${static.public.path}")
	private String staticPuthPath;

	@Value("${productName}")
	private String productName;

	@Value("${copyrightYear}")
	private String copyrightYear;

	@Value("${version}")
	private String version;

	@Value("${web.view.index}")
	private String webViewIndex;

	@Value("${web.maxUploadSize}")
	private Long maxUploadSize;

	@Autowired
	private LogDao logDao;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截器
		LogInterceptor logInterceptor = new LogInterceptor();
		logInterceptor.setIncluded(adminPath);
		logInterceptor.setLogDao(logDao);

		registry.addInterceptor(logInterceptor);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 添加首页跳转
		registry.addRedirectViewController("/", webViewIndex);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 对静态资源文件的访问， 将无法mapping到Controller的path交给default servlet handler处理
		configurer.enable();
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// REST中根据URL后缀自动判定Content-Type及相应的View
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		configurer.ignoreAcceptHeader(true).favorPathExtension(true).mediaTypes(mediaTypes);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 将Jackson2HttpMessageConverter的默认格式化输出为true
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setPrettyPrint(false);
		ObjectMapper objectMapper = vc.thinker.core.mapper.JsonMapper.getInstance();
		jacksonConverter.setObjectMapper(objectMapper);
		converters.add(jacksonConverter);

		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		converters.add(stringConverter);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		SecureModelAttributeMethodArgumentResolver argumentResolver = new SecureModelAttributeMethodArgumentResolver(
				false);
		argumentResolvers.add(argumentResolver);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	public FreeMarkerViewResolver viewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setExposeSpringMacroHelpers(true);
		resolver.setExposeRequestAttributes(true);
		resolver.setExposeSessionAttributes(true);
		resolver.setRequestContextAttribute("request");
		return resolver;
	}

	@Autowired
	@Bean
	public FreeMarkerConfigurer freemarkerConfig(ServletContext servletContext) throws IOException, TemplateException {
		MyFreeMarkerConfigurer factory = new MyFreeMarkerConfigurer();
		factory.setPreTemplateLoaders(new ClassTemplateLoader(SpringApplication.class, "/templates/"));
		factory.setDefaultEncoding("UTF-8");

		Map<String, Object> variables = new HashMap<>();
		Map<String, String> config = new HashMap<>();
		config.put("adminPath", adminPath);
		config.put("staticPuthPath", staticPuthPath);
		config.put("productName", productName);
		config.put("copyrightYear", copyrightYear);
		config.put("version", version);
		variables.put("config", config);
		factory.setFreemarkerVariables(variables);

		Properties settings = new Properties();
		settings.setProperty("default_encoding", "UTF-8");
		settings.setProperty("locale", "zh_CN");
		settings.setProperty("url_escaping_charset", "UTF-8");
		settings.setProperty("date_format", "yyyy-MM-dd");
		settings.setProperty("time_format", "HH:mm:ss");
		settings.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
		settings.setProperty("number_format", "0.######");
		settings.setProperty("boolean_format", "true,false");
		factory.setFreemarkerSettings(settings);

		FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		result.setConfiguration(factory.createConfiguration());
		result.afterPropertiesSet();
		return result;
	}

	@Bean
	@Autowired
	public TaglibFactory taglibFactory(FreeMarkerConfigurer freeMarkerConfigurer) throws IOException, TemplateException {
		TaglibFactory taglibFactory = freeMarkerConfigurer.getTaglibFactory();
		taglibFactory.setObjectWrapper(freemarker.template.Configuration
				.getDefaultObjectWrapper(freemarker.template.Configuration.getVersion()));
		return taglibFactory;
	}

	@Bean
	@ConditionalOnMissingBean(ClassPathTldsLoader.class)
	public ClassPathTldsLoader classPathTldsLoader() {
		return new ClassPathTldsLoader("/templates/tlds/shiros.tld", "/templates/tlds/spring-form.tld",
				"/templates/tlds/fns.tld");
	}

	@Bean
	public SimpleMappingExceptionResolver cabbageMappingExceptionResolver() {
		CabbageMappingExceptionResolver resolver = new CabbageMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.apache.shiro.authz.UnauthorizedException", "error/403");
		mappings.put("java.lang.Throwable", "error/500");
		mappings.put("vc.thinker.biz.exception.ServiceException", "/error/fail");
		resolver.setExceptionMappings(mappings);

		Properties statusCodes = new Properties();
		statusCodes.put("error/500", "500");
		statusCodes.put("error/403", "403");
		resolver.setStatusCodes(statusCodes);
		return resolver;
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {

		MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize(maxUploadSize+"KB"); // KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize(maxUploadSize * 10 + "KB");

		// Sets the directory location wherefiles will be stored.
		// factory.setLocation("路径地址");

		return factory.createMultipartConfig();

	}
	
	@Bean
	public MessageResource messageSource() {
		MessageResource re = new MessageResource();
		return re;
	}

}

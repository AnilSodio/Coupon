package vc.thinker.apiv2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import freemarker.template.TemplateException;
import vc.thinker.apiv2.common.HttpXssObjectMapper;
import vc.thinker.apiv2.web.ApiVersionFilter;
import vc.thinker.apiv2.web.OAuth2UserAuthenticationArgumentResolver;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{

	
	@Value("${api.version}")
	private String version;
	
	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/dist/").setViewName("forward:/dist/index.html");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
		resolver.setPageParameterName("page.page");
		resolver.setSizeParameterName("page.size");

		resolver.setFallbackPageable(new PageRequest(0, 10));
		resolver.setOneIndexedParameters(true);
		argumentResolvers.add(resolver);
		argumentResolvers.add(new OAuth2UserAuthenticationArgumentResolver());

		super.addArgumentResolvers(argumentResolvers);
	}

	@Bean
	public Module jodaModule() {
		return new JodaModule();
	}
	
	 @Bean
	 public MethodValidationPostProcessor methodValidationPostProcessor() {
	      return new MethodValidationPostProcessor();
	 }

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setContentType("text/html; charset=UTF-8");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
		FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
		factory.setTemplateLoaderPaths("classpath:templates", "src/main/resource/templates");
		factory.setDefaultEncoding("UTF-8");
		FreeMarkerConfigurer result = new FreeMarkerConfigurer();
		result.setConfiguration(factory.createConfiguration());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
        .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
        .allowCredentials(false).maxAge(3600);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		// 添加 json 解析
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setPrettyPrint(true);

		ObjectMapper objectMapper = new HttpXssObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		jacksonConverter.setObjectMapper(objectMapper);

		
		StringHttpMessageConverter stringHttpMessageConverter=new StringHttpMessageConverter(Charset.forName("utf-8"));
		converters.add(stringHttpMessageConverter);
		converters.add(jacksonConverter);
	}

	@Bean
	public FilterRegistrationBean apiVersionFilterRegistration(ApiVersionFilter apiVersionFilter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(apiVersionFilter);
		registration.addUrlPatterns("/api/*");
		registration.addInitParameter("latestVersion", version);
		registration.setName("apiVersionFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public ApiVersionFilter apiVersionFilter() {
		return new ApiVersionFilter();
	}
}

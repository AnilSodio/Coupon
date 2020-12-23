package vc.thinker.oauth;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

		HateoasPageableHandlerMethodArgumentResolver resolver = new HateoasPageableHandlerMethodArgumentResolver();
		resolver.setPageParameterName("page.page");
		resolver.setSizeParameterName("page.size");

		resolver.setFallbackPageable(new PageRequest(0, 10));
		resolver.setOneIndexedParameters(true);
		argumentResolvers.add(resolver);

		super.addArgumentResolvers(argumentResolvers);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation is empty.
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
		.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
		.allowCredentials(false).maxAge(3600);
	}
}

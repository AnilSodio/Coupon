package vc.thinker.cabbage.web;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.sitemesh.webapp.SiteMeshFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.support.http.StatViewServlet;

import vc.thinker.cabbage.web.filter.Sitemesh3Filter;
import vc.thinker.core.web.filter.FreemarkerFilter;
import vc.thinker.core.web.servlet.ValidateCodeServlet;

/**
 * web配制
 * @author james
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
    @Bean(name="sitemesh3")
    public Sitemesh3Filter siteMeshFilter(){
        return new Sitemesh3Filter();
    }
    
    /**
     * 编码
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
    
    /**
     * 注册DelegatingFilterProxy（Shiro）
     *
     * @param dispatcherServlet
     * @return
     * @author SHANHY
     * @create  2016年1月13日
     */
    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * sitemesh3
     * @param siteMeshFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(@Qualifier("sitemesh3") SiteMeshFilter siteMeshFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(siteMeshFilter);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
    
    @Bean
    public FreemarkerFilter freemarkerFilter(){
    	FreemarkerFilter filter=new FreemarkerFilter();
    	return filter;
    }	
    /**
     * sitemesh3
     * @param siteMeshFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean freemarkerFilterRegistration(@Qualifier("freemarkerFilter") FreemarkerFilter freemarkerFilter){
    	FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    	filterRegistrationBean.setFilter(freemarkerFilter);
    	filterRegistrationBean.setEnabled(true);
    	filterRegistrationBean.addUrlPatterns("*.ftl");
    	EnumSet<DispatcherType> dispatcherTypes=EnumSet.of(DispatcherType.REQUEST
    			,DispatcherType.FORWARD);
    	filterRegistrationBean.setDispatcherTypes(dispatcherTypes);
    	return filterRegistrationBean;
    }
    
    /**
     * druid 监控 servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
    	StatViewServlet servlet=new StatViewServlet();
    	Map<String, String> initParameters=new HashMap<>();
    	initParameters.put("allow", "127.0.0.1");
    	ServletRegistrationBean servletRegistration= new ServletRegistrationBean(servlet, "/druid/*");// ServletName默认值为首字母小写，即myServlet
    	servletRegistration.setInitParameters(initParameters);
    	return servletRegistration;
    }
    
    /**
     * validateCodeServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean validateCodeServlet() {
    	ValidateCodeServlet servlet=new ValidateCodeServlet();
    	ServletRegistrationBean servletRegistration= 
    			new ServletRegistrationBean(servlet, "/servlet/validateCodeServlet");// ServletName默认值为首字母小写，即myServlet
    	return servletRegistration;
    }
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
       return (container -> {
            ErrorPage error401Page = new ErrorPage(org.springframework.http.HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(org.springframework.http.HttpStatus.NOT_FOUND, "/error/404.html");
            ErrorPage error500Page = new ErrorPage(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
            container.addErrorPages(error401Page, error404Page, error500Page);
       });
    }

}

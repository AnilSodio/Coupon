package vc.thinker.apiv2.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import vc.thinker.apiv2.common.Constants;

/**
 * api 版本过滤器
 * @author james
 *
 */
public class ApiVersionFilter implements Filter{
	
	private float latestVersion;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String conf=filterConfig.getInitParameter("latestVersion");
		if(StringUtils.isBlank(conf)){
			throw new ServletException("filterConfig latestVersion is null");
		}
		try {
			latestVersion=Float.parseFloat(conf);
		} catch (NumberFormatException e) {
			throw new ServletException("filterConfig latestVersion Format error",e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String version=req.getHeader(Constants.API_VERSION_KEY);
		
		//如果版本为空，默认最新版本
		if(StringUtils.isBlank(version)){
			MutableHttpServletRequest mreq=new MutableHttpServletRequest(req);
			mreq.putHeader(Constants.API_VERSION_KEY, String.valueOf(latestVersion));
			chain.doFilter(mreq, response);
		}else{
			try {
				float v=Float.valueOf(version);
				
				if(v < 1.0 || v > latestVersion){
					resp.sendError(HttpStatus.MOVED_PERMANENTLY.value(), "api-version "+v+" 已不支持");
					resp.flushBuffer();
					return;
				}
				chain.doFilter(request, response);
			} catch (NumberFormatException e) {
				resp.sendError(HttpStatus.BAD_REQUEST.value(), "api-version Format error");
				resp.flushBuffer();
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}

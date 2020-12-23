package vc.thinker.cabbage.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 利用client-secret进行请求签名，考虑前端开发的便捷性，暂时不实现
 */
import org.springframework.stereotype.Component;
@Component
public class RequestSignFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//在排除singn参数后，将剩余参数排序后利用user－secret进行md5签名
		//与客户端提交的singn字串对比一致后,提供服务。
		//System.out.println(request.getParameterMap());

		chain.doFilter(request, response);
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}


	@Override
	public void destroy() {}

}

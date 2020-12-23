package vc.thinker.apiv2.web;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.google.common.collect.Lists;

public class OAuth2UserAuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(UserAuthentication.class) != null
                && parameter.getParameterType().equals(org.springframework.security.core.userdetails.User.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
        throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        org.springframework.security.core.userdetails.User auth = null;

        if (authentication.getClass().isAssignableFrom(OAuth2Authentication.class)) {

        	OAuth2Authentication oauth2Auth = ((OAuth2Authentication) authentication);
        	if(oauth2Auth.getUserAuthentication()!=null && oauth2Auth.getUserAuthentication().getPrincipal()!=null){
        		auth = (User) oauth2Auth.getUserAuthentication().getPrincipal();
        	}
            //auth.getUserAuthentication().getPrincipal();
            if(auth==null) auth =  new org.springframework.security.core.userdetails.User("anonymous","",Lists.newArrayList());
        }

        return auth;
    }

}
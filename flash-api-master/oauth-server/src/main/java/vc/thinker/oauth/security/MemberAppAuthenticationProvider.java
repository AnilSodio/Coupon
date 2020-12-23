package vc.thinker.oauth.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import vc.thinker.oauth.security.CustomResourceOwnerPasswordTokenGranter.MemberAppUsernamePasswordAuthenticationToken;


public class MemberAppAuthenticationProvider extends DaoAuthenticationProvider{

	 @Override
	    public boolean supports ( Class<?> authentication ) {
		 	return MemberAppUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	    }
}

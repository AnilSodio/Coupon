package vc.thinker.oauth.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import vc.thinker.oauth.security.CustomResourceOwnerPasswordTokenGranter.MemberWxAppletUsernamePasswordAuthenticationToken;

public class MemberWxAppletAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public boolean supports ( Class<?> authentication ) {
        return MemberWxAppletUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}


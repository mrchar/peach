package com.github.mrchar.peach.authorization.domain.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RestLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, RestLoginConfigurer<H>, RestAuthenticationFilter> {

    public RestLoginConfigurer() {
        super(new RestAuthenticationFilter(), null);
        usernameParameter("username");
        passwordParameter("password");
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    public RestLoginConfigurer<H> usernameParameter(String usernameParameter) {
        getAuthenticationFilter().setUsernameParameter(usernameParameter);
        return this;
    }

    public RestLoginConfigurer<H> passwordParameter(String passwordParameter) {
        getAuthenticationFilter().setPasswordParameter(passwordParameter);
        return this;
    }

    private String getUsernameParameter() {
        return getAuthenticationFilter().getUsernameParameter();
    }

    private String getPasswordParameter() {
        return getAuthenticationFilter().getPasswordParameter();
    }
}

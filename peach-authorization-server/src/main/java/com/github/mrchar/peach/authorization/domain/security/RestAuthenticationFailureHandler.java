package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrchar.peach.authorization.base.model.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    public static final String LOGIN_FAILED_CODE = "LoginFailed";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Response<Object> failureResponse = Response.failure(LOGIN_FAILED_CODE, "login failed", null);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8).toString());
        response.getWriter().write(new ObjectMapper().writeValueAsString(failureResponse));
    }
}

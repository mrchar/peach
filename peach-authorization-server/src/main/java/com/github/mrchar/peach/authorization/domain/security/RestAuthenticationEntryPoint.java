package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrchar.peach.authorization.base.model.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Response<Object> failureResponse = Response.failure("AccessDenied", "请先登录");
        response.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8).toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(failureResponse));
    }
}

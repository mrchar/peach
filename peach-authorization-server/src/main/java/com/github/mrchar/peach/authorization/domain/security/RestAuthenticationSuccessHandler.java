package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrchar.peach.authorization.base.model.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String responseBody = new ObjectMapper().writeValueAsString(Response.success("login success", null));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(responseBody);
    }
}

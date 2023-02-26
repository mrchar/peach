package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrchar.peach.authorization.base.model.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestAccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Response<Object> failureResponse = Response.failure("AccessDenied", "请先登录");
        response.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8).toString());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(failureResponse));
    }
}

package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;

public class RestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public RestAuthenticationFilter() {
        super();
    }

    public RestAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication content type not supported:" + request.getContentType());
        }

        String username;
        String password;
        try {
            BufferedReader reader = request.getReader();
            JsonNode jsonNode = new ObjectMapper().readTree(reader);

            JsonNode usernameJsonNode = jsonNode.get(this.getUsernameParameter());
            username = (usernameJsonNode != null) ? usernameJsonNode.asText().trim() : "";

            JsonNode passwordJsonNode = jsonNode.get(this.getPasswordParameter());
            password = (passwordJsonNode != null) ? passwordJsonNode.asText() : "";
        } catch (IOException e) {
            throw new AuthenticationServiceException("Authentication request payload is empty");
        }

        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

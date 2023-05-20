package net.mrchar.peach.authorization.domain.security;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.BufferedReader;
import java.io.IOException;

public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/api/login", "POST");

    private ObjectMapper mapper = new ObjectMapper();

    public RestUsernamePasswordAuthenticationFilter() {
        super();
        setRequiresAuthenticationRequestMatcher(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    public RestUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication content type not supported:" + request.getContentType());
        }

        String username = "";
        String password = "";
        try {
            BufferedReader reader = request.getReader();
            JsonNode jsonNode = mapper.readTree(reader);
            if (jsonNode != null) {
                username = jsonNode.get(this.getUsernameParameter()).asText();
                password = jsonNode.get(this.getPasswordParameter()).asText();
            }
        } catch (IOException e) {
            throw new AuthenticationServiceException("Authentication request payload is empty");
        }

        username = (username != null) ? username.trim() : "";
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}

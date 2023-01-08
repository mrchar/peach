package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrchar.peach.authorization.base.model.Response;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountSchema;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Optional;

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AccountRepository accountRepository;

    public RestAuthenticationSuccessHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AccountSchema data = null;
        if (authentication instanceof UsernamePasswordAuthenticationToken token) {
            String accountName = token.getName();
            Optional<AccountEntity> optional = this.accountRepository.findOneByName(accountName);
            if (optional.isPresent()) {
                data = AccountSchema.fromEntity(optional.get());
            }
        }

        String responseBody = new ObjectMapper().writeValueAsString(Response.success("login success", data));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(responseBody);
    }
}

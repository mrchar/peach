package com.github.mrchar.peach.authorization.api.controller;

import com.github.mrchar.peach.authorization.api.model.AccountSchema;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.model.RegisterOptions;
import com.github.mrchar.peach.authorization.domain.authentication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AccountService accountService;

    @PostMapping("/register")
    public AccountSchema register(@RequestBody RegisterOptions options) {
        AccountEntity registered = accountService.register(options);
        return AccountSchema.fromEntity(registered);
    }
}

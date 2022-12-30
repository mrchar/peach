package com.github.mrchar.peach.authorization.controller;

import com.github.mrchar.peach.authorization.controller.model.AccountSchema;
import com.github.mrchar.peach.authorization.model.AccountEntity;
import com.github.mrchar.peach.authorization.model.RegisterOptions;
import com.github.mrchar.peach.authorization.service.AccountService;
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

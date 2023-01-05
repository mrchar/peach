package com.github.mrchar.peach.authorization.api.controller;

import com.github.mrchar.peach.authorization.api.model.AccountSchema;
import com.github.mrchar.peach.authorization.base.model.Response;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.model.RegisterOptions;
import com.github.mrchar.peach.authorization.domain.authentication.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "账户")
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AccountService accountService;

    @Operation(summary = "注册账户")
    @PostMapping("/register")
    public Response<AccountSchema> register(@RequestBody RegisterOptions options) {
        AccountEntity registered = accountService.register(options);
        return Response.success(AccountSchema.fromEntity(registered));
    }
}
package com.github.mrchar.peach.authorization.api.controller;

import com.github.mrchar.peach.authorization.api.model.AccountSchema;
import com.github.mrchar.peach.authorization.base.model.Response;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.model.RegisterOptions;
import com.github.mrchar.peach.authorization.domain.authentication.service.AccountService;
import com.github.mrchar.peach.authorization.domain.notification.service.SmsVerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "账户")
@RestController
@RequestMapping("/api/")
public class AuthenticationController {
    private final AccountService accountService;
    private final SmsVerificationService smsVerificationService;

    public AuthenticationController(AccountService accountService,
                                    SmsVerificationService smsVerificationService) {
        this.accountService = accountService;
        this.smsVerificationService = smsVerificationService;
    }

    @Operation(summary = "注册账户")
    @PostMapping("/register")
    public Response<AccountSchema> register(@RequestBody RegisterOptions options) {
        AccountEntity registered = accountService.register(options);
        return Response.success(AccountSchema.fromEntity(registered));
    }

    @Operation(summary = "获取短信验证码")
    @GetMapping("/sms/v-code")
    public Response<Null> sendSmsVerificationCode(@RequestParam("phoneNumber") String phoneNumber){
        this.smsVerificationService.send(phoneNumber);
        return Response.success();
    }
}

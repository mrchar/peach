package net.mrchar.peach.authorization.api.controller;

import net.mrchar.peach.authorization.application.AuthenticationApplicationService;
import net.mrchar.peach.authorization.application.model.AccountSchema;
import net.mrchar.peach.authorization.application.model.RegisterOption;
import net.mrchar.peach.authorization.application.model.SetProfileOption;
import net.mrchar.peach.authorization.common.model.MessageResponse;
import net.mrchar.peach.authorization.common.model.SingletonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "账户")
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationApplicationService authenticationApplicationService;

    @Operation(summary = "注册账户")
    @PostMapping("/register")
    public SingletonResponse<AccountSchema> register(@RequestBody RegisterOption option) {
        AccountSchema accountSchema = this.authenticationApplicationService.register(option);
        return SingletonResponse.success(accountSchema);
    }

    @Operation(summary = "获取短信验证码")
    @GetMapping("/sms/v-code")
    public MessageResponse sendSmsVerificationCode(@RequestParam("phoneNumber") String phoneNumber) {
        this.authenticationApplicationService
                .sendSmsVerificationCode(phoneNumber);
        return MessageResponse.success();
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/self/profile")
    public SingletonResponse<AccountSchema> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountSchema accountSchema = this.authenticationApplicationService
                .getProfileByAccountName(authentication.getName());
        return SingletonResponse.success(accountSchema);
    }

    @Operation(summary = "登记用户信息")
    @PutMapping("/self/profile")
    public SingletonResponse<AccountSchema> setUserProfile(@RequestBody SetProfileOption request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountSchema accountSchema = this.authenticationApplicationService.setProfile(authentication.getName(), request);
        return SingletonResponse.success(accountSchema);
    }
}

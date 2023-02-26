package com.github.mrchar.peach.authorization.application.impl;

import com.github.mrchar.peach.authorization.api.model.AccountSchema;
import com.github.mrchar.peach.authorization.application.AuthenticationApplicationService;
import com.github.mrchar.peach.authorization.application.model.RegisterOptions;
import com.github.mrchar.peach.authorization.application.model.SetProfileOption;
import com.github.mrchar.peach.authorization.domain.user.model.UserEntity;
import com.github.mrchar.peach.authorization.domain.user.model.UserSchema;
import com.github.mrchar.peach.authorization.domain.user.service.PhoneService;
import com.github.mrchar.peach.authorization.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {
    private final UserService userService;
    private final PhoneService phoneService;

    @Override
    public AccountSchema register(RegisterOptions options) {
        return null;
    }

    @Override
    public void sendSmsVerificationCode(String phoneNumber) {

    }

    @Override
    public UserSchema setProfile(String accountName, SetProfileOption userEntity) {
        // 检查验证码
        boolean verified = this.phoneService.verify(userEntity.getPhoneNumber(), userEntity.getSmsVerificationCode());
        if (!verified) {
            throw new IllegalArgumentException("验证码不正确");
        }

        this.userService.updateUser(accountName, new UserEntity());
        return null;
    }
}

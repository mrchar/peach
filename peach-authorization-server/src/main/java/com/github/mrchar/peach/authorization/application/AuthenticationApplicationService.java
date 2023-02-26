package com.github.mrchar.peach.authorization.application;

import com.github.mrchar.peach.authorization.api.model.AccountSchema;
import com.github.mrchar.peach.authorization.application.model.RegisterOptions;
import com.github.mrchar.peach.authorization.application.model.SetProfileOption;
import com.github.mrchar.peach.authorization.domain.user.model.UserSchema;

public interface AuthenticationApplicationService {
    /**
     * 注册账户
     *
     * @param options 注册账户的参数
     * @return 账户信息
     */
    AccountSchema register(RegisterOptions options);

    /**
     * 发送短信验证码
     *
     * @param phoneNumber 接受验证码的手机号码
     */
    void sendSmsVerificationCode(String phoneNumber);

    /**
     * 登记用户信息
     *
     * @param accountName 账户名
     * @param option      用户信息
     * @return 用户信息
     */
    UserSchema setProfile(String accountName, SetProfileOption option);
}

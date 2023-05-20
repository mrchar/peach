package net.mrchar.peach.authorization.application;

import net.mrchar.peach.authorization.application.model.AccountSchema;
import net.mrchar.peach.authorization.application.model.RegisterOption;
import net.mrchar.peach.authorization.application.model.SetProfileOption;

public interface AuthenticationApplicationService {
    /**
     * 注册账户
     *
     * @param option 注册账户的参数
     * @return 账户信息
     */
    AccountSchema register(RegisterOption option);

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
    AccountSchema setProfile(String accountName, SetProfileOption option);

    /**
     * 获取用户的描述信息
     *
     * @param name
     * @return
     */
    AccountSchema getProfileByAccountName(String name);
}

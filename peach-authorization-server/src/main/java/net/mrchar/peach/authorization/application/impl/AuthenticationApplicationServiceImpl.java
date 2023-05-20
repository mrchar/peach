package net.mrchar.peach.authorization.application.impl;

import net.mrchar.peach.authorization.application.AuthenticationApplicationService;
import net.mrchar.peach.authorization.application.model.AccountSchema;
import net.mrchar.peach.authorization.application.model.RegisterOption;
import net.mrchar.peach.authorization.application.model.SetProfileOption;
import net.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import net.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import net.mrchar.peach.authorization.domain.authentication.service.AccountService;
import net.mrchar.peach.authorization.domain.user.model.UserEntity;
import net.mrchar.peach.authorization.domain.user.repository.UserRepository;
import net.mrchar.peach.authorization.domain.user.service.PhoneService;
import net.mrchar.peach.authorization.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationApplicationServiceImpl implements AuthenticationApplicationService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final UserService userService;
    private final PhoneService phoneService;

    @Override
    @Transactional
    public AccountSchema register(RegisterOption option) {
        AccountEntity registered = this.accountService.register(new AccountEntity(
                option.getName(),
                option.getPassword()
        ));
        return AccountSchema.fromEntity(registered);
    }

    @Override
    public void sendSmsVerificationCode(String phoneNumber) {
        this.phoneService.send(phoneNumber);
    }

    @Override
    public AccountSchema setProfile(String accountName, SetProfileOption option) {
        // 检查验证码
        boolean verified = this.phoneService.verify(option.getPhoneNumber(), option.getSmsVerificationCode());
        if (!verified) {
            throw new IllegalArgumentException("验证码不正确");
        }

        UserEntity userEntity = this.userService.updateUser(accountName, new UserEntity());
        return AccountSchema.fromEntity(userEntity.getAccount());
    }

    @Override
    public AccountSchema getProfileByAccountName(String name) {
        AccountEntity accountEntity = this.accountRepository.findOneByName(name)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        return AccountSchema.fromEntity(accountEntity);
    }
}

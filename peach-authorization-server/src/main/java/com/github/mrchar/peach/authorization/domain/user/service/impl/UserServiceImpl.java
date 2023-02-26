package com.github.mrchar.peach.authorization.domain.user.service.impl;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import com.github.mrchar.peach.authorization.domain.user.model.EmailEntity;
import com.github.mrchar.peach.authorization.domain.user.model.PhoneEntity;
import com.github.mrchar.peach.authorization.domain.user.model.UserEntity;
import com.github.mrchar.peach.authorization.domain.user.repository.EmailRepository;
import com.github.mrchar.peach.authorization.domain.user.repository.PhoneRepository;
import com.github.mrchar.peach.authorization.domain.user.repository.UserRepository;
import com.github.mrchar.peach.authorization.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public UserEntity updateUser(String accountName, UserEntity option) {
        // 查找账户
        AccountEntity accountEntity = this.accountRepository.findOneByName(accountName)
                .orElseThrow(() -> new IllegalArgumentException("账户不存在"));

        // 账户对应的用户信息
        UserEntity userEntity = accountEntity.getUser();
        if (userEntity == null) {
            userEntity = new UserEntity(
                    accountEntity,
                    option.getName(),
                    option.getGender(),
                    option.getBirthday()
            );
            this.userRepository.save(userEntity);
        }

        // 设置手机号码
        PhoneEntity phoneEntity = this.updatePhoneNumber(userEntity, option.getPhoneNumber());
        userEntity.setPhone(phoneEntity);

        // 设置电子邮箱
        EmailEntity emailEntity = this.updateEmailAddress(userEntity, option.getEmailAddress());
        userEntity.setEmail(emailEntity);

        return this.userRepository.save(userEntity);
    }

    private PhoneEntity updatePhoneNumber(UserEntity entity, String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }

        // 查找手机号
        PhoneEntity phoneEntity = this.phoneRepository
                .findOneByNumber(phoneNumber)
                .orElse(new PhoneEntity(phoneNumber));

        if (phoneEntity.getUser() != null) {
            throw new IllegalArgumentException("当前手机号已经有用户绑定");
        }

        // 设置所属关系
        phoneEntity.setUser(entity);
        return this.phoneRepository.save(phoneEntity);
    }

    private EmailEntity updateEmailAddress(UserEntity entity, String emailAddress) {
        if (emailAddress == null) {
            return null;
        }

        // 查找电子邮箱
        EmailEntity emailEntity = this.emailRepository
                .findOneByAddress(emailAddress)
                .orElse(new EmailEntity(emailAddress));

        if (emailEntity.getUser() != null) {
            throw new IllegalArgumentException("当前电子邮箱已经有账户绑定");
        }


        emailEntity.setUser(entity);
        return this.emailRepository.save(emailEntity);
    }

}

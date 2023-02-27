package com.github.mrchar.peach.authorization.domain.user.service.impl;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.service.AccountService;
import com.github.mrchar.peach.authorization.domain.common.model.Gender;
import com.github.mrchar.peach.authorization.domain.user.model.UserEntity;
import com.github.mrchar.peach.authorization.domain.user.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@SpringBootTest
class UserServiceImplTest {
    private static final String ACCOUNT_NAME = "account";
    public static final String PHONE_NUMBER = RandomStringUtils.randomNumeric(11);
    public static final String EMAIL_ADDRESS = "email@example.com";
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;


    @Test
    @Transactional
    void updateUser() {
        AccountEntity accountEntity = this.accountService.register(new AccountEntity(ACCOUNT_NAME, "password"));
        UserEntity userEntity = this.userService.updateUser(ACCOUNT_NAME, new UserEntity(
                accountEntity,
                "name",
                Gender.MALE,
                ZonedDateTime.now(),
                PHONE_NUMBER,
                EMAIL_ADDRESS
        ));

        Assertions.assertNotNull(userEntity.getAccount());
        Assertions.assertEquals(ACCOUNT_NAME, userEntity.getAccount().getName());
        Assertions.assertEquals(PHONE_NUMBER, userEntity.getPhoneNumber());
        Assertions.assertEquals(EMAIL_ADDRESS, userEntity.getEmailAddress());
    }
}
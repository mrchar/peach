package com.github.mrchar.peach.authorization.service.impl;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import com.github.mrchar.peach.authorization.domain.authentication.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceImplTest {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Autowired
    AccountService accountService;
    @MockBean
    AccountRepository accountRepository;
    String name = RandomStringUtils.randomAlphanumeric(8, 20);
    String password = RandomStringUtils.randomAlphanumeric(10, 20);

    @Test
    void register() {
        when(this.accountRepository.save(any(AccountEntity.class)))
                .thenAnswer(invocation -> {
                    AccountEntity accountEntity = invocation.getArgument(0);
                    return accountEntity;
                });
        AccountEntity registered = accountService.register(new AccountEntity(name, password));
        assertThat(registered).isNotNull();
        assertThat(registered.getNumber()).isNotNull();
        assertThat(registered.getName()).isEqualTo(name);
        assertThat(passwordEncoder.matches(password, registered.getPassword())).isTrue();
    }
}
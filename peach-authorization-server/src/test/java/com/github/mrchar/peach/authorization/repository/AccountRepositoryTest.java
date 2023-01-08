package com.github.mrchar.peach.authorization.repository;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;
    String number = RandomStringUtils.randomAlphanumeric(8);
    String name = RandomStringUtils.randomAlphanumeric(6, 20);
    String password = RandomStringUtils.randomAlphanumeric(10, 20);

    @BeforeEach
    void setup() {
        AccountEntity accountEntity = new AccountEntity(number, name, password);
        this.accountRepository.saveAndFlush(accountEntity);
    }


    @Test
    void findOneByName() {
        AccountEntity accountEntity = this.accountRepository.findOneByName(name)
                .orElseThrow(() -> new RuntimeException("没有查询到账户名对应的账户"));
        assertEquals(number, accountEntity.getNumber());
        assertEquals(name, accountEntity.getName());
        assertEquals(password, accountEntity.getPassword());
    }
}
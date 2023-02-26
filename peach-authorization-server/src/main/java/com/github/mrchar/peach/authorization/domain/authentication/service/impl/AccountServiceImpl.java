package com.github.mrchar.peach.authorization.domain.authentication.service.impl;

import com.github.mrchar.peach.authorization.application.model.RegisterOptions;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import com.github.mrchar.peach.authorization.domain.authentication.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.github.mrchar.peach.authorization.domain.authentication.service.impl.AccountNumberPrefix.LocalAccountPrefix;

enum AccountNumberPrefix {
    LocalAccountPrefix("A");

    public final String VALUE;

    AccountNumberPrefix(String value) {
        this.VALUE = value;
    }
}

@Service
public class AccountServiceImpl implements AccountService {
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity register(RegisterOptions options) {
        String number = generateLocalAccountNumber();
        String encodedPassword = passwordEncoder.encode(options.getPassword());
        AccountEntity accountEntity = new AccountEntity(number, options.getName(), encodedPassword);
        this.accountRepository.save(accountEntity);
        return accountEntity;
    }

    private String generateLocalAccountNumber() {
        StringBuilder stringBuilder = new StringBuilder(LocalAccountPrefix.VALUE);
        stringBuilder.append(RandomStringUtils.randomNumeric(8));
        return stringBuilder.toString();
    }
}

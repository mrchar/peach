package com.github.mrchar.peach.authorization.domain.authentication.service;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;

public interface AccountService {
    AccountEntity register(AccountEntity option);
}

package com.github.mrchar.peach.authorization.domain.authentication.service;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.model.RegisterOptions;

public interface AccountService {
    AccountEntity register(RegisterOptions options);
}

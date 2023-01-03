package com.github.mrchar.peach.authorization.service;

import com.github.mrchar.peach.authorization.model.AccountEntity;
import com.github.mrchar.peach.authorization.model.RegisterOptions;

public interface AccountService {
    AccountEntity register(RegisterOptions options);
}

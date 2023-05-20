package net.mrchar.peach.authorization.domain.authentication.service;

import net.mrchar.peach.authorization.domain.authentication.model.AccountEntity;

public interface AccountService {
    AccountEntity register(AccountEntity option);
}

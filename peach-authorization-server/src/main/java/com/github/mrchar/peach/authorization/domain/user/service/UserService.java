package com.github.mrchar.peach.authorization.domain.user.service;

import com.github.mrchar.peach.authorization.domain.user.model.UserEntity;

public interface UserService {
    UserEntity updateUser(String accountName, UserEntity option);
}

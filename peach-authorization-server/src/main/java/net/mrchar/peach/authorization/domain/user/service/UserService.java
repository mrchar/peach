package net.mrchar.peach.authorization.domain.user.service;

import net.mrchar.peach.authorization.domain.user.model.UserEntity;

public interface UserService {
    UserEntity updateUser(String accountName, UserEntity option);
}

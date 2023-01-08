package com.github.mrchar.peach.authorization.domain.authentication.model;

import com.github.mrchar.peach.authorization.domain.user.model.UserSchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "账户信息")
@Data
public class AccountSchema {
    private String number;
    private String name;
    private UserSchema user;

    private AccountSchema(String number, String name, UserSchema user) {
        this.number = number;
        this.name = name;
        this.user = user;
    }

    public static AccountSchema fromEntity(AccountEntity entity) {
        UserSchema userSchema = null;
        if (entity.getUser() != null) {
            userSchema = UserSchema.fromEntity(entity.getUser());
        }
        return new AccountSchema(entity.getNumber(), entity.getName(), userSchema);
    }
}

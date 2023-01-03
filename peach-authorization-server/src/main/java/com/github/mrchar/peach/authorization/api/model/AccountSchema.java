package com.github.mrchar.peach.authorization.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Schema(title = "账户信息")
@Getter
@ToString
@EqualsAndHashCode
public class AccountSchema {
    @Schema(title = "账号")
    @JsonProperty("number")
    private String number;

    @Schema(title = "账户名")
    @JsonProperty("name")
    private String name;

    private AccountSchema(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public static AccountSchema fromEntity(AccountEntity entity) {
        return new AccountSchema(entity.getNumber(), entity.getName());
    }
}

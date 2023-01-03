package com.github.mrchar.peach.authorization.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class AccountSchema {
    @JsonProperty("number")
    private String number;
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

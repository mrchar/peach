package com.github.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DefaultLoginParamsImpl implements LoginParams {
    @JsonProperty("name")
    private String name;
    @JsonProperty("password")
    private String password;

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

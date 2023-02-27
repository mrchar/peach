package com.github.mrchar.peach.authorization.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "注册账户参数")
public class RegisterOption {
    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    public RegisterOption() {
    }

    public RegisterOption(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

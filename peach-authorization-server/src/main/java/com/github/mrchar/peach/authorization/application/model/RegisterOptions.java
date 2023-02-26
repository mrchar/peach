package com.github.mrchar.peach.authorization.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterOptions {
    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    public RegisterOptions() {
    }

    public RegisterOptions(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

package com.github.mrchar.peach.authorization.domain.authentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOptions {
    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;


}

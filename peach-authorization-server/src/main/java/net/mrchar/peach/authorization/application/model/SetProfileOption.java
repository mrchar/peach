package net.mrchar.peach.authorization.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.mrchar.peach.authorization.domain.common.model.Gender;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class SetProfileOption {
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("birthday")
    private ZonedDateTime birthday;
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("smsVerificationCode")
    private String smsVerificationCode;
}

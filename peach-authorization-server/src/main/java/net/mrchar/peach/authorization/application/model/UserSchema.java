package net.mrchar.peach.authorization.application.model;

import net.mrchar.peach.authorization.domain.common.model.Gender;
import net.mrchar.peach.authorization.domain.user.model.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;

@Schema(title = "用户信息")
@Data
public class UserSchema {
    private String name;
    private Gender gender;
    private ZonedDateTime birthday;
    private String phoneNumber;
    private String emailAddress;

    public UserSchema(String name,
                      Gender gender, ZonedDateTime birthday,
                      String phoneNumber, String emailAddress) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public static UserSchema fromEntity(UserEntity entity) {
        return new UserSchema(
                entity.getName(),
                entity.getGender(),
                entity.getBirthday(),
                entity.getPhoneNumber(),
                entity.getEmailAddress());
    }
}

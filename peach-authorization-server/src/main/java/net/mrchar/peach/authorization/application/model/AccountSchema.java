package net.mrchar.peach.authorization.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

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

    @Setter(PRIVATE)
    @Schema(title = "用户信息")
    private UserSchema user;

    private AccountSchema(String number, String name) {
        this.number = number;
        this.name = name;
    }


    public static AccountSchema fromEntity(AccountEntity entity) {
        AccountSchema accountSchema = new AccountSchema(entity.getNumber(), entity.getName());
        if (entity.getUser() != null) {
            accountSchema.setUser(UserSchema.fromEntity(entity.getUser()));
        }
        
        return accountSchema;
    }
}

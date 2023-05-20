package net.mrchar.peach.authorization.domain.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Getter
@Entity
@Table(name = "system_phone")
public class PhoneEntity extends AbstractPersistable<UUID> {
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "number")
    private String number;

    @Setter
    @Column(name = "verified")
    private boolean verified;

    public void setNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("手机号码不能为空");
        }

        if (number.startsWith("00")) {
            number = number.replaceAll("^00", "+");
        }

        // TODO: 检查手机号码归属地
        if (!number.startsWith("+")) {
            throw new IllegalArgumentException("手机号码必须以归属地前缀开始");
        }

        this.number = number;
    }

    public PhoneEntity() {
    }

    public PhoneEntity(String number) {
        this.setNumber(number);
    }

    public PhoneEntity(UserEntity user, String number) {
        this.user = user;
        this.setNumber(number);
    }
}

package com.github.mrchar.peach.authorization.domain.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Entity
@Table(name = "system_phone")
public class PhoneEntity extends AbstractPersistable<Long> {
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "number")
    private String number;

    @Setter
    @Column(name = "verified")
    private boolean verified;

    public void setNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("手机号码不能为空");
        }

        // 默认为中国大陆地区前缀
        String prefix = "+86";

        // TODO: 使用工具类解析手机号码前缀

        if (number.startsWith("0086")) {
            number = number.replace("0086", "+86");
        }

        if (number.startsWith("+86")) {
            prefix = "+86";
        } else {
            number = prefix + number;
        }

        this.prefix = prefix;
        this.number = number;
    }

    public PhoneEntity(String number) {
        this.number = number;
    }

    public PhoneEntity(UserEntity user, String number) {
        this.user = user;
        this.setNumber(number);
    }
}

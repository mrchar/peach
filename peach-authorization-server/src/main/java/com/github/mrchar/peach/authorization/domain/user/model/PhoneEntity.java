package com.github.mrchar.peach.authorization.domain.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Entity
@Table(name = "phone")
public class PhoneEntity extends AbstractPersistable<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "number")
    private String number;

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
}

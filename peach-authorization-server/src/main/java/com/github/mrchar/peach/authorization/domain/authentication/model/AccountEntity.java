package com.github.mrchar.peach.authorization.domain.authentication.model;

import com.github.mrchar.peach.authorization.domain.user.model.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Entity
@Table(name = "system_account")
public class AccountEntity extends AbstractPersistable<Long> {
    @Column(name = "number", updatable = false)
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account")
    private UserEntity user;

    public AccountEntity() {
    }

    public AccountEntity(String number, String name, String password) {
        this.number = number;
        this.name = name;
        this.password = password;
    }
}
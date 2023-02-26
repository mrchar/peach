package com.github.mrchar.peach.authorization.domain.user.model;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.common.model.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "`system_user`")
public class UserEntity extends AbstractPersistable<UUID> {
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Setter
    @Column(name = "birthday", columnDefinition = "date")
    private ZonedDateTime birthday;

    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "phone_number",
            referencedColumnName = "number",
            insertable = false,
            updatable = false
    )
    private PhoneEntity phone;

    @Column(name = "email_address")
    private String emailAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "email_address",
            referencedColumnName = "address",
            insertable = false,
            updatable = false
    )
    private EmailEntity email;

    public UserEntity() {
    }

    public UserEntity(
            AccountEntity account,
            String name,
            Gender gender,
            ZonedDateTime birthday) {
        this.account = account;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public UserEntity(
            AccountEntity account,
            String name,
            Gender gender,
            ZonedDateTime birthday,
            String phoneNumber,
            String emailAddress) {
        this.account = account;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public void setPhone(PhoneEntity phone) {
        if (phone == null) {
            this.phoneNumber = null;
            this.phone = null;
            return;
        }

        this.phoneNumber = phone.getNumber();
        this.phone = phone;
    }

    public void setEmail(EmailEntity email) {
        if (email == null) {
            this.emailAddress = null;
            this.email = null;
            return;
        }

        this.emailAddress = email.getAddress();
        this.email = email;
    }
}

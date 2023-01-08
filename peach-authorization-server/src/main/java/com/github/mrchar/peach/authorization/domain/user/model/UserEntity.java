package com.github.mrchar.peach.authorization.domain.user.model;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.common.model.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.ZonedDateTime;

@Getter
@Entity
@Table(name = "`system_user`")
public class UserEntity extends AbstractPersistable<Long> {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birthday")
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

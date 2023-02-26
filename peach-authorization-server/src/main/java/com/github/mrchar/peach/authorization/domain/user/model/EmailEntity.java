package com.github.mrchar.peach.authorization.domain.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Getter
@Entity
@Table(name = "system_email")
public class EmailEntity extends AbstractPersistable<Long> {
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "address")
    private String address;

    @Setter
    @Column(name = "verified")
    private boolean verified;

    public EmailEntity() {
    }

    public EmailEntity(String address) {
        this.address = address;
    }
}

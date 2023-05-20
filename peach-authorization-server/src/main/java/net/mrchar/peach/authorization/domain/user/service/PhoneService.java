package net.mrchar.peach.authorization.domain.user.service;

import net.mrchar.peach.authorization.domain.user.model.PhoneEntity;

public interface PhoneService {
    void send(String phoneNumber);

    boolean verify(String phoneNumber, String verificationCode);

    PhoneEntity addPhone(PhoneEntity entity);
}

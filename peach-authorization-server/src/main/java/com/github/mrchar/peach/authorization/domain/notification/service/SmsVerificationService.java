package com.github.mrchar.peach.authorization.domain.notification.service;

public interface SmsVerificationService {
    void send(String phoneNumber);
    boolean verify(String phoneNumber, String verificationCode);
}

package net.mrchar.peach.authorization.domain.notification.service;

public interface SmsNotificationService {
    void send(String phoneNumber, String message);
}

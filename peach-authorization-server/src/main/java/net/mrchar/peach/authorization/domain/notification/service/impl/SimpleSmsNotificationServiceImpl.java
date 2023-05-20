package net.mrchar.peach.authorization.domain.notification.service.impl;

import net.mrchar.peach.authorization.domain.notification.service.SmsNotificationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleSmsNotificationServiceImpl implements SmsNotificationService {
    @Override
    public void send(String phoneNumber, String message) {
        log.info("Send Message to {}: {}", phoneNumber, message);
    }
}

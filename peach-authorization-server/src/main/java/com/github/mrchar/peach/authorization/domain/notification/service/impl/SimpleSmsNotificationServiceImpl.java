package com.github.mrchar.peach.authorization.domain.notification.service.impl;

import com.github.mrchar.peach.authorization.domain.notification.service.SmsNotificationService;
import com.github.mrchar.peach.authorization.domain.notification.service.SmsVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

@Slf4j
public class SimpleSmsNotificationServiceImpl implements SmsNotificationService {
    @Override
    public void send(String phoneNumber, String message) {
        log.info("Send Message to {}: {}", phoneNumber, message);
    }
}

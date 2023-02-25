package com.github.mrchar.peach.authorization.domain.notification.service.impl;

import com.github.mrchar.peach.authorization.domain.notification.service.SmsNotificationService;
import com.github.mrchar.peach.authorization.domain.notification.service.SmsVerificationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
class VCodeWrapper {
    private LocalDateTime expireAt;
    private String code;
}

public class SimpleSmsVerificationServiceImpl implements SmsVerificationService {
    private static final Duration overtime = Duration.ofMinutes(5);
    private static final Duration refreshTimeout = Duration.ofMinutes(1);
    private final Map<String, VCodeWrapper> wrappers = new HashMap<>();

    private final SmsNotificationService smsNotificationService;

    public SimpleSmsVerificationServiceImpl(SmsNotificationService smsNotificationService) {
        this.smsNotificationService = smsNotificationService;
    }

    @Override
    public void send(String phoneNumber) {
        LocalDateTime now = LocalDateTime.now();
        VCodeWrapper wrapper = wrappers.get(phoneNumber);
        if (wrapper != null && wrapper.getExpireAt().isAfter(now.plus(refreshTimeout))) {
            return;
        }

        String vCode = RandomStringUtils.randomNumeric(6);
        this.smsNotificationService.send(phoneNumber, vCode);

        wrappers.put(phoneNumber, new VCodeWrapper(now.plus(overtime), vCode));
    }

    @Override
    public boolean verify(String phoneNumber, String verificationCode) {
        LocalDateTime now = LocalDateTime.now();
        VCodeWrapper wrapper = wrappers.get(phoneNumber);
        if (wrapper == null) {
            return false;
        }

        if (wrapper.getExpireAt().isAfter(now)) {
            return false;
        }

        return wrapper.getCode().equals(verificationCode);
    }
}

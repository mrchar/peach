package com.github.mrchar.peach.authorization.domain.user.service.impl;

import com.github.mrchar.peach.authorization.domain.notification.service.SmsNotificationService;
import com.github.mrchar.peach.authorization.domain.user.model.PhoneEntity;
import com.github.mrchar.peach.authorization.domain.user.repository.PhoneRepository;
import com.github.mrchar.peach.authorization.domain.user.service.PhoneService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
class VCodeWrapper {
    private LocalDateTime expireAt;
    private String code;
}

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private static final Duration overtime = Duration.ofMinutes(5);
    private static final Duration refreshTimeout = Duration.ofMinutes(1);
    private final Map<String, VCodeWrapper> wrappers = new HashMap<>();
    private final PhoneRepository phoneRepository;
    private final SmsNotificationService smsNotificationService;


    @Override
    @Transactional
    public void send(String phoneNumber) {
        LocalDateTime now = LocalDateTime.now();

        // 检查参数
        if (!StringUtils.hasText(phoneNumber)) {
            throw new IllegalArgumentException("phone number must not empty");
        }

        // 如果记录不存在，那么保存手机号码
        Optional<PhoneEntity> optional = this.phoneRepository.findOneByNumber(phoneNumber);
        if (optional.isEmpty()) {
            this.phoneRepository.save(new PhoneEntity(phoneNumber));
        }

        // 检查之前存在的验证码，如果超时时间小于阈值，不重复发送
        VCodeWrapper wrapper = wrappers.get(phoneNumber);
        if (wrapper != null && wrapper.getExpireAt().isAfter(now.plus(refreshTimeout))) {
            return;
        }

        // 发送验证码
        String vCode = RandomStringUtils.randomNumeric(6);
        this.smsNotificationService.send(phoneNumber, vCode);

        // 记录发送的验证码
        wrappers.put(phoneNumber, new VCodeWrapper(now.plus(overtime), vCode));
    }

    @Override
    @Transactional
    public boolean verify(String phoneNumber, String verificationCode) {
        LocalDateTime now = LocalDateTime.now();

        // 查找手机号码
        Optional<PhoneEntity> optional = this.phoneRepository.findOneByNumber(phoneNumber);
        if (optional.isEmpty()) {
            return false;
        }

        // 检查验证码
        VCodeWrapper wrapper = wrappers.get(phoneNumber);
        if (wrapper == null) {
            return false;
        }

        if (wrapper.getExpireAt().isAfter(now)) {
            return false;
        }

        if (!wrapper.getCode().equals(verificationCode)) {
            return false;
        }

        PhoneEntity phoneEntity = optional.get();
        phoneEntity.setVerified(true);
        this.phoneRepository.save(phoneEntity);
        return true;
    }

    @Override
    public PhoneEntity addPhone(PhoneEntity entity) {
        return null;
    }
}

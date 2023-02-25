package com.github.mrchar.peach.authorization.domain.notification.config;

import com.github.mrchar.peach.authorization.domain.notification.service.SmsNotificationService;
import com.github.mrchar.peach.authorization.domain.notification.service.SmsVerificationService;
import com.github.mrchar.peach.authorization.domain.notification.service.impl.SimpleSmsNotificationServiceImpl;
import com.github.mrchar.peach.authorization.domain.notification.service.impl.SimpleSmsVerificationServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfiguration {

    @Bean
    @ConditionalOnMissingBean(SmsNotificationService.class)
    public SmsNotificationService smsNotificationService() {
        return new SimpleSmsNotificationServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(SmsVerificationService.class)
    public SmsVerificationService smsVerificationService(SmsNotificationService smsNotificationService) {
        return new SimpleSmsVerificationServiceImpl(smsNotificationService);
    }

}

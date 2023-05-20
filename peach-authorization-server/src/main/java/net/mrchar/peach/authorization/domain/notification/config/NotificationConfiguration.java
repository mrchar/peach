package net.mrchar.peach.authorization.domain.notification.config;

import net.mrchar.peach.authorization.domain.notification.service.SmsNotificationService;
import net.mrchar.peach.authorization.domain.notification.service.impl.SimpleSmsNotificationServiceImpl;
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
}

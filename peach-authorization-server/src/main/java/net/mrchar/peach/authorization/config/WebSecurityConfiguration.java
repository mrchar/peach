package net.mrchar.peach.authorization.config;

import net.mrchar.peach.authorization.domain.security.RestAccessDeniedHandler;
import net.mrchar.peach.authorization.domain.security.RestFormLoginConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger/**").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/register").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/login").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/api/sms/v-code").permitAll();
            authorize.anyRequest().authenticated();
        });

        httpSecurity.exceptionHandling(configurer -> {
            configurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
            configurer.accessDeniedHandler(new RestAccessDeniedHandler());
        });

        httpSecurity.apply(new RestFormLoginConfigurer<>());
        httpSecurity.formLogin().disable();
        httpSecurity.httpBasic().disable();
        httpSecurity.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}


package net.mrchar.peach.authorization.config;

import lombok.extern.slf4j.Slf4j;
import net.mrchar.peach.authorization.domain.security.RestAccessDeniedHandler;
import net.mrchar.peach.authorization.domain.security.RestFormLoginConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class WebSecurityConfiguration {
    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsProperties properties) {
        log.info("AllowedOrigins: {}", properties.getAllowOrigins().toString());
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(properties.getAllowOrigins());
        configuration.setAllowedMethods(properties.getAllowMethods());
        configuration.setAllowedHeaders(properties.getAllowHeaders());
        configuration.setExposedHeaders(properties.getExposedHeaders());
        configuration.setAllowCredentials(properties.getAllowCredentials());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger/**").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/register").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/api/login").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/api/sms/v-code").permitAll();
            authorize.anyRequest().authenticated();
        });

        httpSecurity.cors(withDefaults());
        httpSecurity.csrf().disable();

        httpSecurity.exceptionHandling(configurer -> {
            configurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
            configurer.accessDeniedHandler(new RestAccessDeniedHandler());
        });

        httpSecurity
                .apply(new RestFormLoginConfigurer<>())
                .usernameParameter("accountName")
                .passwordParameter("password");
        httpSecurity.formLogin().disable();
        httpSecurity.httpBasic().disable();

        httpSecurity.logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return httpSecurity.build();
    }
}


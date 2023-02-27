package com.github.mrchar.peach.authorization.config;

import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import com.github.mrchar.peach.authorization.domain.security.RestAccessDeniedHandlerImpl;
import com.github.mrchar.peach.authorization.domain.security.RestAuthenticationEntryPoint;
import com.github.mrchar.peach.authorization.domain.security.RestAuthenticationSuccessHandler;
import com.github.mrchar.peach.authorization.domain.security.RestLoginConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfiguration {
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    public WebSecurityConfiguration(AccountRepository accountRepository) {
        this.restAuthenticationSuccessHandler = new RestAuthenticationSuccessHandler(accountRepository);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/", "http://127.0.0.1:5173/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
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

        httpSecurity.apply(
                new RestLoginConfigurer()
                        .successHandler(this.restAuthenticationSuccessHandler));

        httpSecurity.exceptionHandling(customizer -> {
            customizer.authenticationEntryPoint(new RestAuthenticationEntryPoint());
            customizer.accessDeniedHandler(new RestAccessDeniedHandlerImpl());
        });

        httpSecurity.formLogin().disable();
        httpSecurity.httpBasic().disable();
        httpSecurity.logout().disable();
        httpSecurity.cors(withDefaults());
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}


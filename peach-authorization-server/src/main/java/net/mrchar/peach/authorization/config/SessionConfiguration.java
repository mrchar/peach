package net.mrchar.peach.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSpringHttpSession
public class SessionConfiguration {
    private final Map<String, Session> sessionMap = new HashMap<>();

    @Bean
    public SessionRepository<MapSession> sessionRepository() {
        return new MapSessionRepository(sessionMap);
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }
}

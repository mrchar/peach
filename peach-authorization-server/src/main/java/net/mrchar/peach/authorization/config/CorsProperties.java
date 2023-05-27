package net.mrchar.peach.authorization.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private List<String> allowOrigins = List.of("http://localhost:5173/", "http://127.0.0.1:5173/");
    private List<String> allowMethods = List.of("GET", "POST", "PUT", "DELETE", "OPTIONS");
    private List<String> allowHeaders = List.of("*");
    private List<String> exposedHeaders = List.of("X-Auth-Token");
    private Boolean allowCredentials = true;
}

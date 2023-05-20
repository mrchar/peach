package net.mrchar.peach.authorization.domain.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private static final ObjectWriter objectWriter = new ObjectMapper().writer();

    @Data
    @AllArgsConstructor
    public static class RestAccessDeniedResponse {
        private String message;
        private String error;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8).toString());
        String resString = objectWriter.writeValueAsString(
                new RestAccessDeniedResponse(
                        "当前用户无权执行该操作",
                        accessDeniedException.getMessage()
                )
        );
        response.getWriter().write(resString);
        response.getWriter().flush();
    }
}

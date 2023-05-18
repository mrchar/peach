package com.github.mrchar.peach.authorization.config;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import com.github.mrchar.peach.authorization.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SessionConfigurationTest {
    private static final String SESSION_ID_TOKEN = "X-Auth-Token";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void init(){
        AccountEntity accountEntity = new AccountEntity("username", "password");
        this.accountRepository.save(accountEntity);
    }

    @Test
    public void login() throws Exception {
        MvcResult loginFailedResponse = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"username\", \"password\": \"invalid\"}"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();

        Assertions.assertFalse(loginFailedResponse.getResponse()
                .containsHeader(SESSION_ID_TOKEN));

        MvcResult loginSuccessResponse = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"username\", \"password\": \"password\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertTrue(loginSuccessResponse.getResponse()
                .containsHeader(SESSION_ID_TOKEN));

        String sessionId = loginSuccessResponse.getResponse().getHeader(SESSION_ID_TOKEN);

        mockMvc.perform(get("/api/needAuthenticated")
                        .header(SESSION_ID_TOKEN, "invalidSessionId"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/needAuthenticated")
                        .header(SESSION_ID_TOKEN, sessionId))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

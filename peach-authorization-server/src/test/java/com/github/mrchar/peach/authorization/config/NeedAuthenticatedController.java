package com.github.mrchar.peach.authorization.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class NeedAuthenticatedController {
    @GetMapping("/api/needAuthenticated")
    public String needAuthenticated() {
        return "ok";
    }
}

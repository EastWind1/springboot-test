package com.example.springexample.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class LoginListener {
    @EventListener(AuthenticationSuccessEvent.class)
    public void authenticationEvent(AuthenticationSuccessEvent event) {
        log.info("user [{}] login at {}", event.getAuthentication().getName(), LocalDateTime.now());
    }
}

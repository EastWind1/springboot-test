package com.example.springexample.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "test")
@PropertySource(value = "classpath:i18n/i18n.properties", ignoreResourceNotFound = true)
@Getter
@Setter
public class I18nConfig {
    private Map<String, String> test;
    public String getI18n(String key) {
       return test.getOrDefault(key, "");
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        
        return new ServerEndpointExporter();
    }
}

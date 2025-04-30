package com.fdifrison.config;

import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
        // TODO avoid continuos update to token table
    }

}

package com.sfl.tollapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TollConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

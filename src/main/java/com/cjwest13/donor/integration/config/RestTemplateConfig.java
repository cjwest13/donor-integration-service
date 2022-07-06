package com.cjwest13.donor.integration.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Autowired
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        CloseableHttpClient client = HttpClients.createDefault();
        return builder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client)).build();
    }

}
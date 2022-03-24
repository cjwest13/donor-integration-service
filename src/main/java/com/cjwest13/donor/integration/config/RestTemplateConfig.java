package com.cjwest13.donor.integration.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static com.cjwest13.donor.integration.DonorIntegrationServiceContacts.CVS_SECRET;
import static com.cjwest13.donor.integration.DonorIntegrationServiceContacts.CVS_UUID;

@Configuration
public class RestTemplateConfig {

    @Autowired
    CloseableHttpClient httpClient;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }

    public HttpHeaders createHttpHeaders() {
        return new HttpHeaders() {{
            String authStr = CVS_UUID + ":" + CVS_SECRET;
            byte[] base64Creds = Base64.getEncoder().encode(authStr.getBytes());
            String authHeader = "Basic " + new String( base64Creds );
            set( "Authorization", authHeader );
        }};
    }
}

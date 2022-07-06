package com.cjwest13.donor.integration.config;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;

import static com.cjwest13.donor.integration.DonorIntegrationServiceConstants.*;

@Component
public class HttpHeaderConfig {

    public HttpHeaders createActBlueHttpHeaders() {
        return new HttpHeaders() {{
            String authStr = CVS_UUID + ":" + CVS_SECRET;
            byte[] base64Creds = Base64.getEncoder().encode(authStr.getBytes());
            String authHeader = "Basic " + new String( base64Creds );
            set( "Authorization", authHeader );
        }};
    }

    public HttpHeaders createBloomerangHttpHeaders() {
        return new HttpHeaders() {{
            add( "X-API-KEY", BLOOM_KEY );
        }};
    }
}

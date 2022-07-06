package com.cjwest13.donor.integration.bloomerang;

import com.cjwest13.donor.integration.config.HttpHeaderConfig;
import com.cjwest13.donor.integration.exception.BloomerangException;
import com.cjwest13.donor.integration.model.BloomerangSearchListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service("BloomerangAPI")
@Slf4j
public class BloomerangAPIImpl implements BloomerangAPI {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaderConfig header;

    @Value("${bloom.url}")
    private String bloomUrl;

    @Value("${bloom.con.search}")
    private String conSearch;

    /**
     * Searching if Constituent exist by first name and last name
     * @param firstName
     * @param lastName
     * @return BloomerangSearchResponse
     * @throws Exception
     */
    public BloomerangSearchListResponse constituentsSearch(String firstName, String lastName) throws Exception {
        URI uri = UriComponentsBuilder.fromHttpUrl(bloomUrl)
                .path(conSearch)
                .queryParam("search", firstName + " " + lastName)
                .build().toUri();

        HttpEntity request = new HttpEntity(header.createBloomerangHttpHeaders());

        ResponseEntity<BloomerangSearchListResponse> response = restTemplate.exchange(uri, HttpMethod.GET, request, BloomerangSearchListResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            log.error("constituentsSearch returning http status code of: " + response.getStatusCode());
            throw new BloomerangException("constituentsSearch returning http status code of: " + response.getStatusCode());
        }
    }

}

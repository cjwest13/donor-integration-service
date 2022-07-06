package com.cjwest13.donor.integration.bloomerang;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplicationTests;
import com.cjwest13.donor.integration.actblue.ActBlueAPIImpl;
import com.cjwest13.donor.integration.config.HttpHeaderConfig;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import com.cjwest13.donor.integration.model.BloomerangSearchListResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.cjwest13.donor.integration.TestUtils.getSearchList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
public class BloomerangAPITests extends DonorIntegrationServiceApplicationTests {

    @Mock
    RestTemplate restTemplate;

    @Mock
    HttpHeaderConfig header;

    @InjectMocks
    BloomerangAPIImpl api;

    @Value("${bloom.url}")
    private String bloomUrl;

    @Value("${bloom.con.search}")
    private String conSearch;

    @Value("${json.dir}")
    private String jsonDir;

    @Value("${json.bloom.response}")
    private String jsonResponseFile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(header.createBloomerangHttpHeaders()).thenReturn(new HttpHeaders());
        ReflectionTestUtils.setField(api, "bloomUrl", bloomUrl);
        ReflectionTestUtils.setField(api, "conSearch", conSearch);
    }

    @Test
    public void constituentsSearch() throws Exception {
        ActBlueDonation actBlueDonation = new ActBlueDonation();
        actBlueDonation.setDonorFirstName("Jennifer");
        actBlueDonation.setDonorLastName("Near");
        BloomerangSearchListResponse searchListResponse = getSearchList(jsonDir, jsonResponseFile);

        ResponseEntity responseEntity = new ResponseEntity<>(searchListResponse, HttpStatus.OK);

        when(restTemplate.exchange(any(), any(), any(), any(Class.class))).thenReturn(responseEntity);

        BloomerangSearchListResponse response = api.constituentsSearch(actBlueDonation.getDonorFirstName(), actBlueDonation.getDonorLastName());

        assertEquals(searchListResponse.getResults().size(), response.getResults().size());
    }

}
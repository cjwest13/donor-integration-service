package com.cjwest13.donor.integration.actblue;

import com.cjwest13.donor.integration.model.ActBlueRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

public class ActBlue {

    @Autowired
    RestTemplate restTemplate;

    @Value("actblue.url")
    private String cvsUrl;

    @Value("actblue.get.cvs.path")
    private String getCvsPath;

    public void getActBlueCSV() {
        URI uri = UriComponentsBuilder.fromPath(cvsUrl)
                .path(getCvsPath)
                .build().toUri();

//        HttpEntity request = new HttpEntity(createHttpHeaders);
//        restTemplate.
    }

    protected ActBlueRequestBody createRequestBody() {
        Date date = new Date();
        ActBlueRequestBody actBlueRequestBody = new ActBlueRequestBody();
        actBlueRequestBody.setCsv_type("paid_contributions");
        actBlueRequestBody.setDate_range_start("03-01-2022");
        actBlueRequestBody.setDate_range_end(date.toString());
        return actBlueRequestBody;
    }
}

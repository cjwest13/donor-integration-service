package com.cjwest13.donor.integration.actblue;

import com.cjwest13.donor.integration.config.HttpHeaderConfig;
import com.cjwest13.donor.integration.exception.ActBlueException;
import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;
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

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.cjwest13.donor.integration.utils.DonorIntegrationServiceUtils.*;

@Service("ActBlueAPI")
@Slf4j
public class ActBlueAPIImpl implements ActBlueAPI {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaderConfig header;

    @Value("${actblue.url}")
    private String cvsUrl;

    @Value("${actblue.cvs.path}")
    private String cvsPath;

    @Value("${stage.dir}")
    private String stageDir;

    /**
     * Calling the Create CVS API of ActBlue.
     * @param dateStart
     * @param dateEnd
     * @param cvsType
     * @return ActBlueCreateCVSResponse
     * @throws Exception
     */
    public ActBlueCreateCVSResponse createActBlueCSV(Date dateStart, Date dateEnd, String cvsType) throws Exception {
        URI uri = UriComponentsBuilder.fromHttpUrl(cvsUrl)
                .path(cvsPath)
                .build().toUri();

        HttpEntity request = new HttpEntity(createActBlueRequestBody(dateStart, dateEnd, cvsType), header.createActBlueHttpHeaders());

        ResponseEntity<ActBlueCreateCVSResponse> response = restTemplate.postForEntity(uri, request, ActBlueCreateCVSResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            log.error("createActBlueCSV returning http status code of: " + response.getStatusCode());
            throw new ActBlueException("createActBlueCSV returning http status code of: " + response.getStatusCode());
        }

    }

    /**
     * Calling the Get CVS API of ActBlue utilizing payload of Create CVS API .
     * @param createCVSResponse
     * @return ActBlueGetCVSResponse
     * @throws Exception
     */
    public ActBlueGetCVSResponse getActBlueCSV(ActBlueCreateCVSResponse createCVSResponse) throws Exception {
        URI uri = UriComponentsBuilder.fromHttpUrl(cvsUrl)
                .path(cvsPath)
                .pathSegment(createCVSResponse.getId())
                .build().toUri();

        HttpEntity request = new HttpEntity(header.createActBlueHttpHeaders());

        ResponseEntity<ActBlueGetCVSResponse> response = restTemplate.exchange(uri, HttpMethod.GET, request, ActBlueGetCVSResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            log.error("getActBlueCSV returning http status code of: " + response.getStatusCode());
            throw new ActBlueException("getActBlueCSV returning http status code of: " + response.getStatusCode());
        }

    }

    @SuppressWarnings("unchecked")
    public List<ActBlueDonation> getActBlueDonations(String downloadUrl) throws Exception {
        Path filePath =  Paths.get(stageDir + File.separator + UUID.randomUUID()).normalize();
        downloadFileFromUrl(filePath, downloadUrl);
        return (List<ActBlueDonation>) getCSVAsObject(filePath.toString(), ActBlueDonation.class);
    }

}
package com.cjwest13.donor.integration.actblue;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplicationTests;
import com.cjwest13.donor.integration.config.HttpHeaderConfig;
import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
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
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
public class ActBlueAPITests extends DonorIntegrationServiceApplicationTests {

    @Mock
    RestTemplate restTemplate;

    @Mock
    HttpHeaderConfig header;

    @InjectMocks
    ActBlueAPIImpl actBlue;

    @Value("${test.csv.dir}")
    private String testCsvDir;

    @Value("${test.csv}")
    private String testCsv;

    @Value("${stage.dir}")
    private String stageDir;

    @Value("${actblue.url}")
    private String cvsUrl;

    @Value("${actblue.cvs.path}")
    private String cvsPath;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(header.createActBlueHttpHeaders()).thenReturn(new HttpHeaders());
        ReflectionTestUtils.setField(actBlue, "cvsUrl", cvsUrl);
        ReflectionTestUtils.setField(actBlue, "cvsPath", cvsPath);
        ReflectionTestUtils.setField(actBlue, "stageDir", stageDir);
    }

    @AfterEach
    public void cleanUp() {
        File dir = new File(stageDir);
        for (File file: dir.listFiles()) {
            file.delete();
        }
    }


    @Test
    public void testCreateActBlueCSV() throws Exception {
        String pattern = "yyyy-MM-dd";
        String dateStart = "2022-03-01";
        String dateEnd = "2022-04-01";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = simpleDateFormat.parse(dateStart);
        Date endDate = simpleDateFormat.parse(dateEnd);
        String cvsType = "paid_contributions";

        ActBlueCreateCVSResponse actBlueCVSExpected = new ActBlueCreateCVSResponse();
        actBlueCVSExpected.setId("asdhuas-sasadsaj");
        when(restTemplate.postForEntity(any(), any(), any()))
                .thenReturn(new ResponseEntity<>(actBlueCVSExpected, HttpStatus.OK));

        ActBlueCreateCVSResponse actBlueMethodResponse = actBlue.createActBlueCSV(startDate, endDate, cvsType);

        assertEquals(actBlueCVSExpected.getId(), actBlueMethodResponse.getId(), "Create Response Id");
    }

    @Test
    public void testGetActBlueCSV() throws Exception {
        ActBlueCreateCVSResponse createCVSResponse = new ActBlueCreateCVSResponse();
        createCVSResponse.setId("asdhuas-sasadsaj");

        ActBlueGetCVSResponse cvsExpected = new ActBlueGetCVSResponse();
        cvsExpected.setId("asdhuas-sasadsaj");
        cvsExpected.setDownload_url("http//downloading.something");
        cvsExpected.setStatus("completed");

        ResponseEntity responseEntity = new ResponseEntity<>(cvsExpected, HttpStatus.OK);

        when(restTemplate.exchange( any(), any(), any(), any(Class.class))).thenReturn(responseEntity);

        ActBlueGetCVSResponse response = actBlue.getActBlueCSV(createCVSResponse);

        assertEquals(cvsExpected.getId(), response.getId(), "Get Response Id");
        assertEquals(cvsExpected.getDownload_url(), response.getDownload_url(), "Get Response Download URL");
        assertEquals(cvsExpected.getStatus(), response.getStatus(), "Get Response Status");
    }

    @Test
    public void testGetActBlueDonations() throws Exception {
        URL url = Paths.get(testCsvDir, (testCsv)).normalize().toUri().toURL();
        List<ActBlueDonation> actBlueDonations = actBlue.getActBlueDonations(url.toString());

        assertTrue(actBlueDonations.size() == 266, "Act Blue Donations Size");
    }

}
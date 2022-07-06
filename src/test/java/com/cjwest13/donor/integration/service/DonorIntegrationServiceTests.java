package com.cjwest13.donor.integration.service;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplicationTests;
import com.cjwest13.donor.integration.actblue.ActBlueAPIImpl;
import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DonorIntegrationServiceTests extends DonorIntegrationServiceApplicationTests {

    @Mock
    ActBlueAPIImpl actBlue;

    @InjectMocks
    @Autowired
    DonorIntegrationServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActBlueToBloom() throws Exception {
        String pattern = "yyyy-MM-dd";
        String dateStart = "2022-03-01";
        String dateEnd = "2022-04-01";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = simpleDateFormat.parse(dateStart);
        Date endDate = simpleDateFormat.parse(dateEnd);
        String cvsType = "paid_contributions";

        ActBlueCreateCVSResponse actBlueCreateCvs = new ActBlueCreateCVSResponse();
        actBlueCreateCvs.setId("asdhuas-sasadsaj");
        ActBlueGetCVSResponse actBlueGetCvs = new ActBlueGetCVSResponse();
        actBlueGetCvs.setStatus("complete");

        when(actBlue.createActBlueCSV(any(), any(), anyString())).thenReturn(actBlueCreateCvs);
        when(actBlue.getActBlueCSV(actBlueCreateCvs)).thenReturn(actBlueGetCvs);

        Boolean response = service.actBlueToBloom(startDate, endDate, cvsType);

        assertEquals(true, response, "Service Response");
    }
}

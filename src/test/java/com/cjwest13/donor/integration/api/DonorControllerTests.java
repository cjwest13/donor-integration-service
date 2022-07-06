package com.cjwest13.donor.integration.api;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplicationTests;
import com.cjwest13.donor.integration.service.DonorIntegrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DonorControllerTests extends DonorIntegrationServiceApplicationTests {

    @Mock
    private DonorIntegrationServiceImpl donorIntegrationService;

    @InjectMocks
    private DonorController donorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActBlueToBloomerangPositive() throws Exception {
        HttpStatus expected = HttpStatus.OK;

        when(donorIntegrationService.actBlueToBloom(any(), any(), any()))
                .thenReturn(true);

        ResponseEntity<?> response = donorController.actBlueToBloomerang(new Date(), new Date(), "paid_contributions");

        assertEquals(expected, response.getStatusCode(), "HttpStatus Okay");
    }

    @Test
    public void testActBlueToBloomerangTestNegative() throws Exception {
        HttpStatus expected = HttpStatus.INTERNAL_SERVER_ERROR;

        when(donorIntegrationService.actBlueToBloom(any(), any(), any()))
                .thenReturn(false);

        ResponseEntity<?> response = donorController.actBlueToBloomerang(new Date(), new Date(), "paid_contributions");

        assertEquals(expected, response.getStatusCode(), "HttpStatus Internal Server Error");
    }
}

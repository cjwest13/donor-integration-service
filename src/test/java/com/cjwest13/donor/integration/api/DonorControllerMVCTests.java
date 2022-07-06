package com.cjwest13.donor.integration.api;

import com.cjwest13.donor.integration.service.DonorIntegrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class DonorControllerMVCTests {

    private MockMvc mockMvc;

    @Mock
    private DonorIntegrationServiceImpl donorIntegrationService;

    @InjectMocks
    @Autowired
    private DonorController donorController;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this);

        when(donorIntegrationService.actBlueToBloom(any(), any(), any()))
                .thenReturn(true);

        this.mockMvc = standaloneSetup(donorController).build();

    }

    @Test
    public void testActBlueToBloomerang() throws Exception {
        mockMvc.perform(get("/donor/actBlueToBloom?dateStart=2022-02-01&dateEnd=2022-03-01&cvsType=paid_contributions"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testActBlueToBloomerangBadStartDate() throws Exception {
        mockMvc.perform(get("/donor/actBlueToBloom?dateStart=2202-01&dateEnd=2022-03-01&cvsType=paid_contributions"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testActBlueToBloomerangBadEndDate() throws Exception {
        mockMvc.perform(get("/donor/actBlueToBloom?dateStart=2022-02-01&dateEnd=2022-99-01&cvsType=paid_contributions"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testActBlueToBloomerangNoCvsType() throws Exception {
        mockMvc.perform(get("/donor/actBlueToBloom?dateStart=2022-02-01&dateEnd=2022-03-01"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
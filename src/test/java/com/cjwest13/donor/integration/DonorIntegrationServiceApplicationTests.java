package com.cjwest13.donor.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.cjwest13.donor.integration.DonorIntegrationServiceConstants.JUNIT_PROFILE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(JUNIT_PROFILE)
public class DonorIntegrationServiceApplicationTests {

	@Autowired
	public WebApplicationContext context;

	@Test
	void testContext() {
		assertNotNull(context);
	}

}
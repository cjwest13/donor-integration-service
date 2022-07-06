package com.cjwest13.donor.integration.utils;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplicationTests;
import com.cjwest13.donor.integration.model.ActBlueCreateCVSRequest;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class DonorIntegrationServiceUtilsTests extends DonorIntegrationServiceApplicationTests {

    @Value("${stage.dir}")
    private String stageDir;

    @Value("${test.csv.dir}")
    private String testCsvDir;

    @Value("${test.csv}")
    private String testCsv;

    private DonorIntegrationServiceUtils donorIntegrationServiceUtils = new DonorIntegrationServiceUtils();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void cleanUp() {
        File dir = new File(stageDir);
        for (File file: dir.listFiles()) {
            file.delete();
        }
    }

    @Test
    public void testcreateActBlueRequestBody() throws Exception {
        String pattern = "yyyy-MM-dd";
        String dateStart = "2022-03-01";
        String dateEnd = "2022-04-01";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = simpleDateFormat.parse(dateStart);
        Date endDate = simpleDateFormat.parse(dateEnd);
        String cvsType = "paid_contributions";

        ActBlueCreateCVSRequest response = donorIntegrationServiceUtils.createActBlueRequestBody(startDate, endDate, cvsType);

        assertEquals(cvsType, response.getCsv_type(), "CVS Type");
        assertEquals(dateStart, response.getDate_range_start(), "Date Start");
        assertEquals(dateEnd, response.getDate_range_end(), "Date End");
    }

    @Test
    public void testFormatDateAsString() throws Exception {
        String pattern = "yyyy-MM-dd";
        String date = "2022-03-01";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = simpleDateFormat.parse(date);

        String response = donorIntegrationServiceUtils.formatDateAsString(startDate);

        assertEquals(date, response, "Date");

    }

    @Test
    public void testDownloadFileFromUrl() throws Exception {

        Path expectedFilePath = Paths.get(stageDir, (UUID.randomUUID().toString() + ".csv")).normalize();
        URL url = Paths.get(testCsvDir, (testCsv)).normalize().toUri().toURL();
        File dir = new File(stageDir);

        donorIntegrationServiceUtils.downloadFileFromUrl(expectedFilePath, url.toString());
        File[] files = dir.listFiles();

        assertTrue(files.length == 1, "Files length is one");
        assertTrue(files[0].getName().equals(expectedFilePath.getFileName().toString()), "File Name in Dir match excepted");

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetCSVAsObject() throws Exception {
        String filePath = Paths.get(testCsvDir, (testCsv)).normalize().toString();
        List<ActBlueDonation> actBlueDonations = (List<ActBlueDonation>) donorIntegrationServiceUtils.getCSVAsObject(
                filePath, ActBlueDonation.class);

        log.info(String.valueOf(actBlueDonations.get(0)));

        assertTrue(actBlueDonations.size() == 266, "Act Blue Donations Size");
    }

}
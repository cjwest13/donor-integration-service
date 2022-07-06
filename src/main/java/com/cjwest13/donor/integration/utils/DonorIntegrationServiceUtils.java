package com.cjwest13.donor.integration.utils;

import com.cjwest13.donor.integration.model.ActBlueCreateCVSRequest;
import com.cjwest13.donor.integration.exception.ActBlueException;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public class DonorIntegrationServiceUtils {

    public static ActBlueCreateCVSRequest createActBlueRequestBody(Date dateStart, Date dateEnd, String cvsType) {
        ActBlueCreateCVSRequest actBlueRequestBody = new ActBlueCreateCVSRequest();
        actBlueRequestBody.setCsv_type(cvsType);
        actBlueRequestBody.setDate_range_start(formatDateAsString(dateStart));
        actBlueRequestBody.setDate_range_end(formatDateAsString(dateEnd));
        return actBlueRequestBody;

    }

    public static String formatDateAsString(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);

    }

    public static void downloadFileFromUrl(Path filePath, String fileUrl) throws ActBlueException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile());
             FileChannel fileChannel = fileOutputStream.getChannel();
             InputStream in = new URL(fileUrl).openStream();
             ReadableByteChannel readableByteChannel = Channels.newChannel(in)) {

            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        } catch (Exception e) {
            log.error("Error has occurred in downloadFileFromUrl: " + e.getMessage());
            throw new ActBlueException("Error has occurred in downloadFileFromUrl: " + e.getMessage(), e.getCause());
        }

    }

    @SuppressWarnings("unchecked")
    public static List<?> getCSVAsObject(String filePath, Class<?> clazz) throws Exception {
        return new CsvToBeanBuilder(new FileReader(filePath))
                .withType(clazz)
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build()
                .parse();
    }

}

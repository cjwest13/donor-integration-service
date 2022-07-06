package com.cjwest13.donor.integration;

import com.cjwest13.donor.integration.model.BloomerangSearchListResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class TestUtils {

    public static BloomerangSearchListResponse getSearchList(String jsonDir, String jsonResponseFile) throws IOException {
        Path filePath =  Paths.get(jsonDir + File.separator + jsonResponseFile).normalize();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(filePath);
            return new Gson().fromJson(reader, BloomerangSearchListResponse.class);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}

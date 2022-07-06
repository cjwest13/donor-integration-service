package com.cjwest13.donor.integration.model;

import lombok.Data;

@Data
public class ActBlueCreateCVSRequest {
    private String csv_type;

    private String date_range_start;

    private String date_range_end;
}

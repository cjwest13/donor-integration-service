package com.cjwest13.donor.integration.model;

import lombok.Data;

import java.util.Date;

@Data
public class ActBlueRequestBody {
    private String csv_type;

    private String date_range_start;

    private String date_range_end;
}

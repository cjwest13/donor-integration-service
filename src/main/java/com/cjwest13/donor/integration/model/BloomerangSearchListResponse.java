package com.cjwest13.donor.integration.model;

import com.cjwest13.donor.integration.domain.Address;
import com.cjwest13.donor.integration.domain.Email;
import lombok.Data;

import java.util.List;

@Data
public class BloomerangSearchListResponse {
    private int ResultCount;
    private List<BloomerangSearchResponse> Results;

}
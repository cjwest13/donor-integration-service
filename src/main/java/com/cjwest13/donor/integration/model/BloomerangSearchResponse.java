package com.cjwest13.donor.integration.model;

import com.cjwest13.donor.integration.domain.Address;
import com.cjwest13.donor.integration.domain.CustomValue;
import com.cjwest13.donor.integration.domain.Email;
import lombok.Data;

import java.util.List;

@Data
public class BloomerangSearchResponse {

    private int Id;
    private int AccountNumber;
    private String Status;
    private String Type;
    private String FirstName;
    private String LastName;
    private String JobTitle;
    private String Employer;
    private Address PrimaryAddress;
    private Email PrimaryEmail;
    private Address PrimaryPhone;
    private List<CustomValueSearch> CustomValues;
}
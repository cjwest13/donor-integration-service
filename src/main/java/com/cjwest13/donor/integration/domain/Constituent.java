package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Constituent implements Serializable {

    private String Type = "Individual";
    private String Status = "Active";
    private String FirstName;
    private String LastName;
    private String JobTitle;
    private String Employer;
    private Email PrimaryEmail;
    private Phone PrimaryPhone;
    private Address PrimaryAddress;
    private List<CustomValue> CustomValues; //Receipt ID
}
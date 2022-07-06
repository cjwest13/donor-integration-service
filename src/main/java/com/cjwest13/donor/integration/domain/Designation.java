package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Designation implements Serializable {

    private double Amount;
    private String Note;
    private String Type = "Donations";
    private int FundId = 10; //General
//    private int fundId = 218112 //Test
    private List<CustomValue> CustomValues; //Payment ID

}
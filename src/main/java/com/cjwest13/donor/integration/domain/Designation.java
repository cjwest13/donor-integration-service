package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Designation implements Serializable {

    private double Amount;
    private String Note;
    private String Type;
    private int FundId;
    private List<CustomValue> CustomValues; //Payment ID

}
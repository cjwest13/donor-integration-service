package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Transaction implements Serializable {

    private Date Date;
    private double Amount;
    private String Method = "CreditCard";
    private String CreditCardType;
    private String AccountId;
    private List<Designation> Designations;

}

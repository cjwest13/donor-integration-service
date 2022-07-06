package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Donation implements Serializable {

    private Constituent constituent;
    private List<Transaction> transactionList;

}
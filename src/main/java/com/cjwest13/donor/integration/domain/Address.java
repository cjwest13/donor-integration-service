package com.cjwest13.donor.integration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    private String Type;
    private String Street;
    private String City;
    private String State;
    private String PostalCode;
    private String Country;
}

package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Phone implements Serializable {
    private String Type;
    private String Number;
    private boolean IsPrimary = true;
}

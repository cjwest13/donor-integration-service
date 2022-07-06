package com.cjwest13.donor.integration.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Email implements Serializable {
    private String Type;
    private String Value;
    private boolean IsPrimary = true;
}

package com.cjwest13.donor.integration.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomValueSearch implements Serializable {

    private int FieldId;
    private ValueSearch Value;

}

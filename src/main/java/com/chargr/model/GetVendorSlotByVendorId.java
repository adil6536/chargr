package com.chargr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetVendorSlotByVendorId {

    private int id;
    private int vendorId;
    private int amount;
    private int status;
    private String date;
    private String startTime;
    private String endTime;
}

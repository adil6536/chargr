package com.chargr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetBankDetailsByVendorId {

    private int id;
    private int vendorId;
    private String accountNumber;
    private String accountHolderName;
    private String branchName;
    private String ifscCode;
}

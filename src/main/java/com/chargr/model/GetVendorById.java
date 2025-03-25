package com.chargr.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetVendorById {

    private int id;
    private int roleId;
    private int businessId;
    private String name;
    private String shopName;
    private String mobile;
    private String email;
    private String password;
    private String location;
    private String startTime;
    private String endTime;
    private int status;
}

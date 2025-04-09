package com.chargr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllVendors {

    private int id;
    private int roleId;
    private int businessId;
    private String name;
    private String shopName;
    private String mobile;
    private String email;
    private String location;
    private String defaultStartTime;
    private String defaultEndTime;
    private int status;
    private String extraStartTime;
    private String extraEndTime;
}

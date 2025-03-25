package com.chargr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetUserBookingByUserId {

    private int id;
    private int userId;
    private int vendorId;
    private int slotDuration;
    private int amount;
    private String date;
    private String startTime;
    private String endTime;
    private String bookingStatus;
    private String VendorName;
    private String vendorMobile;
    private String vendorEmail;
    private String location;
    private String shopName;

}

package com.chargr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetUserBookingByVendorId {

    private int id;
    private int userId;
    private int vendorId;
    private int slotDuration;
    private int amount;
    private String date;
    private String startTime;
    private String endTime;
    private String bookingStatus;
    private String userName;
    private String userMobile;
    private String userEmail;

}

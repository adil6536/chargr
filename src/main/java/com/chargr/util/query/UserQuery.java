package com.chargr.util.query;

public class UserQuery {

    public static final String CreateUserBooking = "insert into user_booking (user_id, vendor_id, slot_duration,amount, start_time, end_time, status) values (?,?,?,?,?,?,?);";

    public static final String UpdateUserBooking = "update user_booking set slot_duration = ?, amount = ?, start_time = ?, end_time = ?, status = ? where id = ? ;";

    public static final String GetUserBookingByUserId = "select ub.id, ub.user_id, ub.vendor_id, ub.slot_duration, ub.amount, date(ub.created_on) date, ub.start_time, ub.end_time, ub.status booking_status, v.name vendor_name,v.mobile vendor_mobile,\n" +
            "v.email vendor_email,v.shop_name,v.location from user_booking ub\n" +
            "inner join vendor v on ub.vendor_id = v.id where ub.user_id = ? and v.status = 1;";

    public static final String GetUserBookingByVendorId = "select ub.id, ub.user_id, ub.vendor_id, ub.slot_duration, ub.amount, date(ub.created_on) date, ub.start_time, ub.end_time, ub.status booking_status, u.name user_name,\n" +
            "u.mobile user_mobile,u.email user_email from user_booking ub\n" +
            "inner join user u on ub.user_id = u.id where ub.vendor_id = ? and u.status = 1;";

    public static final String GetUserByEmail = "select id, role_id, name, email, mobile, google_token, status from user where email = ?";

    public static final String UpdateUserBookingStatus = "update user_booking set status = ? where id = ?";

    public static final String UpdateUserStatus = "update user set status = ? where email = ?";

    public static final String validateUserEmail = "select email from user where email = ?";

    public static final String CreateUser = "insert into user (role_id, name, mobile, email, google_token, status) values (?,?,?,?,?,?);";


}

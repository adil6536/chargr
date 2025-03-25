package com.chargr.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chargr.model.GetUserBookingByUserId;
import com.chargr.model.GetUserByEmail;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.chargr.model.GetUserBookingByVendorId;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserUtil {

    public ResultSetExtractor<Boolean> getBooleanResultSetExtractor() {
        return new ResultSetExtractor<Boolean>() {
            public Boolean extractData(ResultSet resultSet) throws SQLException,
                    DataAccessException {
                if (resultSet.next()) {
                    return true;
                }
                return false;
            }
        };
    }

    public GetUserBookingByUserId prepareUserBookingByUserIdObject(ResultSet rs) throws SQLException {
        GetUserBookingByUserId data = new GetUserBookingByUserId();
        data.setId(rs.getInt("id"));
        data.setUserId(rs.getInt("user_id"));
        data.setVendorId(rs.getInt("vendor_id"));
        data.setSlotDuration(rs.getInt("slot_duration"));
        data.setAmount(rs.getInt("amount"));
        data.setDate(rs.getString("date"));
        data.setStartTime(rs.getString("start_time"));
        data.setEndTime(rs.getString("end_time"));
        data.setBookingStatus(rs.getString("booking_status"));
        data.setVendorName(rs.getString("vendor_name"));
        data.setVendorMobile(rs.getString("vendor_mobile"));
        data.setVendorEmail(rs.getString("vendor_email"));
        data.setLocation(rs.getString("location"));
        data.setShopName(rs.getString("shop_name"));

        return data;
    }

    public GetUserBookingByVendorId prepareUserBookingByVendorIdObject(ResultSet rs) throws SQLException {
        GetUserBookingByVendorId data = new GetUserBookingByVendorId();
        data.setId(rs.getInt("id"));
        data.setUserId(rs.getInt("user_id"));
        data.setVendorId(rs.getInt("vendor_id"));
        data.setSlotDuration(rs.getInt("slot_duration"));
        data.setAmount(rs.getInt("amount"));
        data.setDate(rs.getString("date"));
        data.setStartTime(rs.getString("start_time"));
        data.setEndTime(rs.getString("end_time"));
        data.setBookingStatus(rs.getString("booking_status"));
        data.setUserName(rs.getString("user_name"));
        data.setUserMobile(rs.getString("user_mobile"));
        data.setUserEmail(rs.getString("user_email"));

        return data;
    }

    public GetUserByEmail prepareUserByEmailObject(ResultSet rs) throws SQLException {
        GetUserByEmail data = new GetUserByEmail();
        data.setId(rs.getInt("id"));
        data.setRoleId(rs.getInt("role_id"));
        data.setName(rs.getString("name"));
        data.setEmail(rs.getString("email"));
        data.setMobile(rs.getString("mobile"));
        data.setGoogleToken(rs.getString("google_token"));
        data.setStatus(rs.getInt("status"));



        return data;
    }



}

package com.chargr.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.chargr.model.GetAllVendors;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.chargr.dto.response.ResponseBody;
import com.chargr.model.GetBankDetailsByVendorId;
import com.chargr.model.GetVendorById;
import com.chargr.model.GetVendorSlotByVendorId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VendorUtil {

    public static Gson getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
        }.getType(), new MapDeserializerDoubleAsIntFix());

        return gsonBuilder.create();
    }

    public static ResponseBody response(int status, String message) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(status);
        responseBody.setMessage(message);
        return responseBody;
    }


    public static ResponseBody response(int status, String message, Object data) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setStatus(status);
        responseBody.setMessage(message);
        responseBody.setData(data);
        return responseBody;
    }

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

    public ResultSetExtractor<Integer> getIntegerResultSetExtractor(){
        return new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet resultSet) throws SQLException,
                    DataAccessException {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
                return 0;
            }
        };
    }

    public GetVendorById prepareVendorByIdObject(ResultSet rs) throws SQLException {
        GetVendorById data = new GetVendorById();
        data.setId(rs.getInt("id"));
        data.setRoleId(rs.getInt("role_id"));
        data.setBusinessId(rs.getInt("business_id"));
        data.setName(rs.getString("name"));
        data.setShopName(rs.getString("shop_name"));
        data.setMobile(rs.getString("mobile"));
        data.setEmail(rs.getString("email"));
        data.setPassword(rs.getString("password"));
        data.setLocation(rs.getString("location"));
        data.setStartTime(rs.getString("start_time"));
        data.setEndTime(rs.getString("end_time"));
        data.setStatus(rs.getInt("status"));
        return data;
    }

    public GetAllVendors prepareGetAllVendorObject(ResultSet rs) throws SQLException {
        GetAllVendors data = new GetAllVendors();
        data.setId(rs.getInt("id"));
        data.setRoleId(rs.getInt("role_id"));
        data.setBusinessId(rs.getInt("business_id"));
        data.setName(rs.getString("name"));
        data.setShopName(rs.getString("shop_name"));
        data.setMobile(rs.getString("mobile"));
        data.setEmail(rs.getString("email"));
        data.setLocation(rs.getString("location"));
        data.setDefaultStartTime(rs.getString("default_start_time"));
        data.setDefaultEndTime(rs.getString("default_end_time"));
        data.setStatus(rs.getInt("status"));
        data.setExtraStartTime(rs.getString("extra_start_time"));
        data.setExtraEndTime(rs.getString("extra_end_time"));
        return data;
    }

    public GetBankDetailsByVendorId prepareBankDetailsByIdObject(ResultSet rs) throws SQLException {
        GetBankDetailsByVendorId data = new GetBankDetailsByVendorId();
        data.setId(rs.getInt("id"));
        data.setVendorId(rs.getInt("vendor_id"));
        data.setAccountNumber(rs.getString("account_number"));
        data.setAccountHolderName(rs.getString("account_holder_name"));
        data.setBranchName(rs.getString("branch_name"));
        data.setIfscCode(rs.getString("ifsc_code"));
        return data;
    }

    public GetVendorSlotByVendorId prepareVendorSlotByIdObject(ResultSet rs) throws SQLException {
        GetVendorSlotByVendorId data = new GetVendorSlotByVendorId();
        data.setId(rs.getInt("id"));
        data.setVendorId(rs.getInt("vendor_id"));
        data.setAmount(rs.getInt("amount"));
        data.setStatus(rs.getInt("status"));
        data.setDate(rs.getString("date"));
        data.setStartTime(rs.getString("start_time"));
        data.setEndTime(rs.getString("end_time"));
        return data;
    }

}

package com.chargr.dto.request;

import java.sql.Timestamp;

import com.chargr.util.ParameterValidator;
import com.chargr.util.VendorConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;


@ToString
public class CreateUpdateVendor {

    private Integer id;

    private Integer bussinessId;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_ ]{3,20}$")
    private String name;

    private String shopName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotEmpty
    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String mobile;

    @NotNull
    @NotEmpty
    @NotBlank
    private String location;

    @NotNull
    @NotEmpty
    @NotBlank
    private String startTime;

    @NotNull
    @NotEmpty
    @NotBlank
    private String endTime;

    private int status;


    private JsonObject customError = new JsonObject();


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Integer bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ParameterValidator.validateString(name);
        if (VendorConstants.BAD_REQUEST_CODE_STRING.equals(this.name))
            customError.addProperty("name", 1);
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = ParameterValidator.validateString(shopName);
        if (VendorConstants.BAD_REQUEST_CODE_STRING.equals(this.shopName))
            customError.addProperty("shop_name", 1);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = ParameterValidator.validateString(email);
        if (VendorConstants.BAD_REQUEST_CODE_STRING.equals(this.email))
            customError.addProperty("email", 1);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = ParameterValidator.validateString(mobile);
        if (VendorConstants.BAD_REQUEST_CODE_STRING.equals(this.mobile))
            customError.addProperty("mobile", 1);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JsonObject getCustomError() {
        return customError;
    }

    public void setCustomError(JsonObject customError) {
        this.customError = customError;
    }

}

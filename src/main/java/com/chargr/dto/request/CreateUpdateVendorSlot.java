package com.chargr.dto.request;

import com.google.gson.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;

public class CreateUpdateVendorSlot {

    private Integer id;

    @NotNull
    private Integer vendorId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String amount;

    @NotNull
    private int status;

    @NotNull
    @NotEmpty
    @NotBlank
    private String date;

    @NotNull
    @NotEmpty
    @NotBlank
    private String startTime;

    @NotNull
    @NotEmpty
    @NotBlank
    private String endTime;

    private JsonObject customError = new JsonObject();


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public JsonObject getCustomError() {
        return customError;
    }

    public void setCustomError(JsonObject customError) {
        this.customError = customError;
    }

}

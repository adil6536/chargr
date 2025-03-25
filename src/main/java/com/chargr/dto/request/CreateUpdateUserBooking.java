package com.chargr.dto.request;

import com.google.gson.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateUpdateUserBooking {

    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer vendorId;

    @NotNull
    private int slotDuration;

    @NotNull
    private int amount;

    @NotNull
    private String status;

    @NotNull
    @NotEmpty
    @NotBlank
    private String startTime;

    @NotNull
    @NotEmpty
    @NotBlank
    private String endTime;

    private JsonObject customError = new JsonObject();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

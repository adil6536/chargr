package com.chargr.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;

import java.sql.Timestamp;

public class UpdateVendorStartEndTime {

    private Integer id;

    private String startTime;

    private String endTime;

    private JsonObject customError = new JsonObject();


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

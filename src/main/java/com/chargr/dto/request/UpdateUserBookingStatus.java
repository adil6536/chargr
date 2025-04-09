package com.chargr.dto.request;

import com.google.gson.JsonObject;

public class UpdateUserBookingStatus {

    private String status;

    private int id;

    private JsonObject customError = new JsonObject();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public JsonObject getCustomError() {
        return customError;
    }

    public void setCustomError(JsonObject customError) {
        this.customError = customError;
    }
}

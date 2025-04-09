package com.chargr.dto.request;

import com.google.gson.JsonObject;

public class UpdateStatus {

    private int status;

    private int id;

    private JsonObject customError = new JsonObject();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}

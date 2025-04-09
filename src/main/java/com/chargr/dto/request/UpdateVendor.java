package com.chargr.dto.request;

import com.chargr.util.ParameterValidator;
import com.chargr.util.VendorConstants;
import com.google.gson.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateVendor {

    @NotNull
    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_ ]{3,20}$")
    private String name;

    @NotEmpty
    @NotBlank
    private String password;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ParameterValidator.validateString(name);
        if (VendorConstants.BAD_REQUEST_CODE_STRING.equals(this.name))
            customError.addProperty("name", 1);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

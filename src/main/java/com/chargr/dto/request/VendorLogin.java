package com.chargr.dto.request;

import com.google.gson.JsonObject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VendorLogin {

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotNull
    @NotEmpty
    private String password;

    private JsonObject customError = new JsonObject();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JsonObject getCustomError() {
        return customError;
    }

    public void setCustomError(JsonObject customError) {
        this.customError = customError;
    }

}

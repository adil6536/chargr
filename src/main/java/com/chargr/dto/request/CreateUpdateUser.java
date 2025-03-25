package com.chargr.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

public class CreateUpdateUser {

    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_ ]{3,20}$")
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^\\d{10}$")
    private String mobile;

    @NotNull
    @NotEmpty
    @NotBlank
    private String googleToken;

    private JsonObject customError = new JsonObject();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }



    public JsonObject getCustomError() {
        return customError;
    }

    public void setCustomError(JsonObject customError) {
        this.customError = customError;
    }
}

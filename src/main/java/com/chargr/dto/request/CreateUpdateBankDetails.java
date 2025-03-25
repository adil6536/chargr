package com.chargr.dto.request;

import com.google.gson.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

@ToString
public class CreateUpdateBankDetails {

    private Integer id;

    @NotNull
    private Integer vendorId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String accountNumber;

    @NotNull
    @NotEmpty
    @NotBlank
    private String accountHolderName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String branchName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String ifscCode;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branch_name) {
        this.branchName = branch_name;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public JsonObject getCustomError() {
        return customError;
    }

    public void setCustomError(JsonObject customError) {
        this.customError = customError;
    }

}

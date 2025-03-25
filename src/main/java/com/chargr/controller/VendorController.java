package com.chargr.controller;



import com.chargr.dto.request.VendorLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chargr.dto.request.CreateUpdateBankDetails;
import com.chargr.dto.request.CreateUpdateVendor;
import com.chargr.dto.request.CreateUpdateVendorSlot;
import com.chargr.dto.request.UpdateVendorStartEndTime;
import com.chargr.dto.response.ResponseBody;
import com.chargr.service.VendorService;
import com.chargr.util.ParameterValidator;
import com.google.gson.JsonObject;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins ="*", methods = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
@Validated
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    ParameterValidator parameterValidator;
    
    @PostMapping("/loginVendor")
    public ResponseEntity loginVendor(@Valid @RequestBody VendorLogin vendorLogin, BindingResult validationResults) {
        JsonObject pathVariable = new JsonObject();
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, vendorLogin.getCustomError());
        if (responseBody == null)
            responseBody = vendorService.loginVendor(vendorLogin.getEmail(),vendorLogin.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/updateVendorStartEndTime")
    public ResponseEntity updateVendorStartEndTime(@Valid @RequestBody UpdateVendorStartEndTime updateVendorStartEndTime, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, updateVendorStartEndTime.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.updateVendorStartEndTime(updateVendorStartEndTime);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/createVendor")
    public ResponseEntity createVendor(@Valid @RequestBody CreateUpdateVendor createUpdateVendor, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, createUpdateVendor.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.createVendor(createUpdateVendor);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/updateVendor")
    public ResponseEntity updateVendor(@Valid @RequestBody CreateUpdateVendor updateVendor, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, updateVendor.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.updateVendor(updateVendor);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/deleteVendor/{vendorId}")
    public ResponseEntity deleteVendor(@PathVariable("vendorId") int vendorId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("vendorId", vendorId);
        ResponseBody responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = vendorService.deleteVendor(vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/getVendorById/{vendorId}")
    public ResponseEntity getVendorById(@PathVariable("vendorId") int vendorId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("vendorId", vendorId);
        ResponseBody responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = vendorService.getVendorById(vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/getAllVendors")
    public ResponseEntity getAllVendors() {
       ResponseBody responseBody = vendorService.getAllVendors();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/insertBankDetails")
    public ResponseEntity insertBankDetails(@Valid @RequestBody CreateUpdateBankDetails createBankDetails, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, createBankDetails.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.insertBankDetails(createBankDetails);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/updateBankDetails")
    public ResponseEntity updateBankDetails(@Valid @RequestBody CreateUpdateBankDetails updateBankDetails, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, updateBankDetails.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.updateBankDetails(updateBankDetails);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/getBankDetailsByVendorId/{vendorId}")
    public ResponseEntity getBankDetailsByVendorId(@PathVariable("vendorId") int vendorId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("vendorId", vendorId);
        ResponseBody responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = vendorService.getBankDetailsByVendorId(vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/deleteVendorBank/{bankId}")
    public ResponseEntity deleteVendorBank(@PathVariable("bankId") int bankId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("bankId", bankId);
        ResponseBody responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = vendorService.deleteVendorBank(bankId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/insertVendorSlot")
    public ResponseEntity insertVendorSlot(@Valid @RequestBody CreateUpdateVendorSlot createVendorSlot, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, createVendorSlot.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.insertVendorSlot(createVendorSlot);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/UpdateVendorSlot")
    public ResponseEntity UpdateVendorSlot(@Valid @RequestBody CreateUpdateVendorSlot updateVendorSlot, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, updateVendorSlot.getCustomError());
        if (responseBody == null) {
            responseBody = vendorService.UpdateVendorSlot(updateVendorSlot);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/GetVendorSlotByVendorId/{vendorId}")
    public ResponseEntity GetVendorSlotByVendorId(@PathVariable("vendorId") int vendorId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("vendorId", vendorId);
        ResponseBody<?> responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = vendorService.GetVendorSlotByVendorId(vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


}

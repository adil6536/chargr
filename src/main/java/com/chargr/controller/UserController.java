package com.chargr.controller;

import com.chargr.dto.request.CreateUpdateUser;
import com.chargr.dto.request.UpdateStatus;
import com.chargr.dto.request.UpdateUserBookingStatus;
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

import com.chargr.dto.request.CreateUpdateUserBooking;
import com.chargr.dto.response.ResponseBody;
import com.chargr.service.UserService;
import com.chargr.util.ParameterValidator;
import com.google.gson.JsonObject;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins ="*", methods = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ParameterValidator parameterValidator;

    @PostMapping("/insertUserBooking")
    public ResponseEntity insertUserBooking(@Valid @RequestBody CreateUpdateUserBooking createUpdateUserBooking, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, createUpdateUserBooking.getCustomError());
        if (responseBody == null) {
            responseBody = userService.insertUserBooking(createUpdateUserBooking);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/updateUserBooking")
    public ResponseEntity updateUserBooking(@Valid @RequestBody CreateUpdateUserBooking createUpdateUserBooking, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, createUpdateUserBooking.getCustomError());
        if (responseBody == null) {
            responseBody = userService.updateUserBooking(createUpdateUserBooking);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/getUserBookingByUserId/{userId}")
    public ResponseEntity getUserBookingById(@PathVariable("userId") int userId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("userId", userId);
        ResponseBody responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = userService.getUserBookingByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/getUserBookingByVendorId/{vendorId}")
    public ResponseEntity getUserBookingByVendorId(@PathVariable("vendorId") int vendorId) {
        JsonObject pathVariable = new JsonObject();
        pathVariable.addProperty("vendorId", vendorId);
        ResponseBody responseBody = parameterValidator.validatePathVariable(pathVariable, 1);
        if (responseBody == null)
            responseBody = userService.getUserBookingByVendorId(vendorId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/updateUserBookingStatus")
    public ResponseEntity updateUserBookingStatus(@Valid @RequestBody UpdateUserBookingStatus updateUserBookingStatus, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, updateUserBookingStatus.getCustomError());
        if (responseBody == null)
            responseBody = userService.updateUserBookingStatus(updateUserBookingStatus.getStatus(),updateUserBookingStatus.getId());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/createUpdateUser")
    public ResponseEntity createUpdateUser(@Valid @RequestBody CreateUpdateUser createUpdateUser, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, createUpdateUser.getCustomError());
        if (responseBody == null) {
            responseBody = userService.createUpdateUser(createUpdateUser);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/updateUserStatus")
    public ResponseEntity updateUserStatus(@Valid @RequestBody UpdateStatus updateStatus, BindingResult validationResults) {
        ResponseBody responseBody = parameterValidator.getErrors(validationResults, updateStatus.getCustomError());
        if (responseBody == null)
            responseBody = userService.updateUSerStatus(updateStatus.getStatus(),updateStatus.getId());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}

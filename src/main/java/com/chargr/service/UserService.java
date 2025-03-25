package com.chargr.service;

import java.util.ArrayList;
import java.util.List;

import com.chargr.dto.request.CreateUpdateUser;
import com.chargr.model.GetUserBookingByUserId;
import com.chargr.model.GetUserByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chargr.dto.request.CreateUpdateUserBooking;
import com.chargr.dto.response.ResponseBody;
import com.chargr.model.GetUserBookingByVendorId;
import com.chargr.repository.UserRepo;
import com.chargr.util.UserConstants;
import com.chargr.util.VendorConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public ResponseBody<ArrayList<Object>> insertUserBooking(CreateUpdateUserBooking createUpdateUserBooking){
        ResponseBody<ArrayList<Object>> responseBody = new ResponseBody();
        if(userRepo.insertUserBooking(createUpdateUserBooking) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_ADDED_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody<ArrayList<Object>> updateUserBooking(CreateUpdateUserBooking createUpdateUserBooking){
        ResponseBody<ArrayList<Object>> responseBody = new ResponseBody();
        if(userRepo.updateUserBooking(createUpdateUserBooking) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_EDIT_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_EDIT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody getUserBookingByUserId(int id){
        ResponseBody<List<GetUserBookingByUserId>> responseBody = new ResponseBody();
        List<GetUserBookingByUserId> data = new ArrayList<>();
        data = userRepo.getUserBookingByUserId(id);

        if(data.isEmpty()){
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.DATABASE_ERROR_MSG);
            responseBody.setData(new ArrayList<>());
        } else {
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_FOUND_MSG);
            responseBody.setData(data);
        }
        return responseBody;
    }

    public ResponseBody getUserBookingByVendorId(int vendorId){
        ResponseBody<List<GetUserBookingByVendorId>> responseBody = new ResponseBody();
        List<GetUserBookingByVendorId> data = new ArrayList<>();
            data = userRepo.getUserBookingByVendorId(vendorId);

        if(data == null){
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.DATABASE_ERROR_MSG);
            responseBody.setData(new ArrayList<>());
        } else {
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_FOUND_MSG);
            responseBody.setData(data);
        }
        return responseBody;
    }

    public ResponseBody updateUserBookingStatus(String status,int id ){
        ResponseBody responseBody = new ResponseBody();
        if(userRepo.updateUserBookingStatus(status,id) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_STATUS_UPDATE_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(UserConstants.USER_BOOKING_STATUS_UPDATE_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody createUpdateUser(CreateUpdateUser createUpdateUser){
        ResponseBody responseBody = new ResponseBody();
        System.out.println(userRepo.validateUserEmail(createUpdateUser.getEmail()));
        if (userRepo.validateUserEmail(createUpdateUser.getEmail())) {
            if(userRepo.updateUserStatusWithEmail(1,createUpdateUser.getEmail())>0) {
            	GetUserByEmail data = userRepo.getUserByEmail(createUpdateUser.getEmail());
                responseBody.setStatus(VendorConstants.SUCCESS_CODE);
                responseBody.setMessage(UserConstants.USER_STATUS_UPDATE_SUCCESS_MSG);
                responseBody.setData(data);
            } else {
                responseBody.setStatus(VendorConstants.ERROR_CODE);
                responseBody.setMessage(UserConstants.USER_STATUS_UPDATE_FAILED_MSG);
                responseBody.setData(new ArrayList<>());
            }
        } else{
            int row = userRepo.createUser(createUpdateUser);
            if(row > 0){
                GetUserByEmail data = userRepo.getUserByEmail(createUpdateUser.getEmail());
                responseBody.setStatus(VendorConstants.SUCCESS_CODE);
                responseBody.setMessage(UserConstants.USER_ADDED_SUCCESS_MSG);
                responseBody.setData(data);
            }else {
                responseBody.setStatus(VendorConstants.ERROR_CODE);
                responseBody.setMessage(UserConstants.USER_FAILED_MSG);
                responseBody.setData(new ArrayList<>());
            }
        }
        return responseBody;
    }

}

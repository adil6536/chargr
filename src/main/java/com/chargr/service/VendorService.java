package com.chargr.service;

import java.util.ArrayList;
import java.util.List;

import com.chargr.dto.request.CreateUpdateChargerType;
import com.chargr.dto.request.UpdateVendor;
import com.chargr.model.GetAllVendors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chargr.dto.request.CreateUpdateBankDetails;
import com.chargr.dto.request.CreateUpdateVendor;
import com.chargr.dto.request.CreateUpdateVendorSlot;
import com.chargr.dto.request.UpdateVendorStartEndTime;
import com.chargr.dto.response.ResponseBody;
import com.chargr.model.GetBankDetailsByVendorId;
import com.chargr.model.GetVendorById;
import com.chargr.model.GetVendorSlotByVendorId;
import com.chargr.repository.VendorRepo;
import com.chargr.util.VendorConstants;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepo;
    
    public ResponseBody loginVendor(String email, String password){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.vendorLogin(email,password) > 0){
            GetVendorById data = vendorRepo.getVendorByEmail(email);
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_LOGIN_SUCCESS_MSG);
            responseBody.setData(data);
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_LOGIN_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody updateVendorStartEndTime(UpdateVendorStartEndTime updateVendorStartEndTime){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.updateVendorStartEndTime(updateVendorStartEndTime) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_EDIT_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_EDIT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody createVendor(CreateUpdateVendor createUpdateVendor){
        ResponseBody responseBody = new ResponseBody();
        if (vendorRepo.validateVendorEmail(createUpdateVendor.getEmail())) {
            responseBody.setStatus(VendorConstants.USER_DEFINED_ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_EMAIL_ALREADY_EXIST_MSG);
            responseBody.setData(new ArrayList<>());
        } else if (vendorRepo.validateVendorMobile(createUpdateVendor.getMobile())) {
            responseBody.setStatus(VendorConstants.USER_DEFINED_ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_MOBILE_ALREADY_EXIST_MSG);
            responseBody.setData(new ArrayList<>());
        } else{
            int row =vendorRepo.insertVendor(createUpdateVendor);
            if(row > 0){
                GetVendorById data = vendorRepo.getVendorByEmail(createUpdateVendor.getEmail());
                responseBody.setStatus(VendorConstants.SUCCESS_CODE);
                responseBody.setMessage(VendorConstants.VENDOR_ADDED_SUCCESS_MSG);
                responseBody.setData(data);
            }else {
                responseBody.setStatus(VendorConstants.ERROR_CODE);
                responseBody.setMessage(VendorConstants.VENDOR_FAILED_MSG);
                responseBody.setData(new ArrayList<>());
            }
        }
        return responseBody;
    }

    public ResponseBody updateVendor(UpdateVendor updateVendor){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.updateVendor(updateVendor) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_EDIT_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_EDIT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody deleteVendor(int vendorId){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.deleteVendor(vendorId) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_DELETE_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_DELETE_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody getVendorById(int vendorId){
        ResponseBody responseBody = new ResponseBody();
        GetVendorById data = vendorRepo.getVendorById(vendorId);
        if(data == null){
            log.error("Error occured while fetching data from DB");
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.DATABASE_ERROR_MSG);
            responseBody.setData(new ArrayList<>());
        } else {
            log.info("Fetching kit data completed successfully");
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_FOUND_MSG);
            responseBody.setData(data);
        }
        return responseBody;
    }

    public ResponseBody getAllVendors(){
        ResponseBody responseBody = new ResponseBody();
        List<GetAllVendors> data = vendorRepo.getAllVendors();
        if(data.isEmpty()){
            log.error("No vendors found");
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.NO_VENDORS_FOUND);
            responseBody.setData(new ArrayList<>());
        } else {
            log.info("Fetching vendors data completed successfully");
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_FOUND_MSG);
            responseBody.setData(data);
        }
        return responseBody;
    }

    public ResponseBody insertBankDetails(CreateUpdateBankDetails createBankDetails){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.insertBankDetails(createBankDetails) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.BANK_ADDED_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.BANK_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody updateBankDetails(CreateUpdateBankDetails updateBankDetails){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.updateBankDetails(updateBankDetails) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.BANK_EDIT_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.BANK_EDIT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody getBankDetailsByVendorId(int vendorId){
        ResponseBody responseBody = new ResponseBody();
        List<GetBankDetailsByVendorId> data = vendorRepo.getBankDetailsByVendorId(vendorId);
        if(data == null){
            log.error("Error occured while fetching data from DB");
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.DATABASE_ERROR_MSG);
            responseBody.setData(new ArrayList<>());
        } else {
            log.info("Fetching vendor bank data completed successfully");
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_BANK_FOUND_MSG);
            responseBody.setData(data);
        }
        return responseBody;
    }

    public ResponseBody deleteVendorBank(int bankId){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.deleteVendorBank(bankId) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_BANK_DELETE_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_BANK_DELETE_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody insertVendorSlot(CreateUpdateVendorSlot createVendorSlot){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.insertVendorSlot(createVendorSlot) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_SLOT_ADDED_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_SLOT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody UpdateVendorSlot(CreateUpdateVendorSlot updateVendorSlot){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.UpdateVendorSlot(updateVendorSlot) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_SLOT_EDIT_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_SLOT_EDIT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody GetVendorSlotByVendorId(int vendorId){
        ResponseBody responseBody = new ResponseBody();
        List<GetVendorSlotByVendorId> data = vendorRepo.GetVendorSlotByVendorId(vendorId);
        if(data == null){
            log.error("Error occured while fetching data from DB");
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.DATABASE_ERROR_MSG);
            responseBody.setData(new ArrayList<>());
        } else {
            log.info("Fetching vendor slot data completed successfully");
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_SLOT_FOUND_MSG);
            responseBody.setData(data);
        }
        return responseBody;
    }

    public ResponseBody updateVendorStatus(int status,int id){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.updateVendorStatus(status,id) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_STATUS_UPDATE_SUCCESS);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.VENDOR_STATUS_UPDATE_FAILED);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody createChargerType(CreateUpdateChargerType createChargerType){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.createChargerType(createChargerType) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.CHARGER_TYPE_ADDED_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.CHARGER_TYPE_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }

    public ResponseBody updateChargerType(CreateUpdateChargerType updateChargerType){
        ResponseBody responseBody = new ResponseBody();
        if(vendorRepo.updateChargerType(updateChargerType) > 0){
            responseBody.setStatus(VendorConstants.SUCCESS_CODE);
            responseBody.setMessage(VendorConstants.CHARGER_TYPE_EDIT_SUCCESS_MSG);
            responseBody.setData(new ArrayList<>());
        }else{
            responseBody.setStatus(VendorConstants.ERROR_CODE);
            responseBody.setMessage(VendorConstants.CHARGER_TYPE_EDIT_FAILED_MSG);
            responseBody.setData(new ArrayList<>());
        }
        return responseBody;
    }
}

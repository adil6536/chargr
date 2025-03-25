package com.chargr.util;

import static com.chargr.util.VendorConstants.*;
import static com.chargr.util.VendorUtil.response;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.chargr.dto.response.ResponseBody;
import com.google.gson.JsonObject;

@Component
public class ParameterValidator {

    // 1 - only numeric , 2 - only uppercase character
    private String getPatternRegex(int type){
        String regex = "^[a-zA-Z0-9 ]+$";
        if(type == 1){
            regex = "^[0-9]+$";
        } else if (type == 2) {
            regex = "^[A-Z]+$";
        } else if (type == 3){
            regex = "^\\d{4}-\\d{2}-\\d{2}$";
        }
        return regex;
    }

    public ResponseBody validatePathVariable(JsonObject pathVariable, int type){
        ResponseBody errors = null;
        if(pathVariable.size() > 0){
            for(String key : pathVariable.keySet()){
                if(!pathVariable.get(key).getAsString().matches(getPatternRegex(type))){
                    errors = response(BAD_REQUEST_CODE, BAD_REQUEST_STRING);
                }
            }
        }
        return  errors;
    }


    public ResponseBody getErrors(BindingResult validationResults, JsonObject customError) {

        ResponseBody errors = null;
        if (validationResults.hasErrors() || customError.size() > 0) {
            errors = response(BAD_REQUEST_CODE, BAD_REQUEST_STRING);
        }
        return errors;
    }

    public static String validateString(Object value){
        String data = TYPE_INVALID_STRING;
        if((value instanceof String)) {
            data = value.toString();
        }
        return data;
    }

    public static Integer validateInteger(Object value){
        Integer data = TYPE_INVALID_CODE;
        if((value instanceof Integer)) {
            data = Integer.parseInt(value.toString());
        }
        return data;
    }
}

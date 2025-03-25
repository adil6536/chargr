package com.chargr.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBody<T> implements Serializable {
    private int status;
    private String message;
    private T data;
}

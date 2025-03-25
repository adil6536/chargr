package com.chargr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetUserByEmail {

    private int id;
    private int roleId;
    private String name;
    private String email;
    private String mobile;
    private String googleToken;
    private int status;
}

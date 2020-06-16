package com.onepointpropertybackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileModel {
    private int id;
    private String username;
    private String password;
    private int role;
    private boolean status;

}



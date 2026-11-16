package com.app.ecom.dto;

import com.app.ecom.model.UserRole;

public class userResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole = UserRole.CUSTOMER;
    private AddressDTO address;
}

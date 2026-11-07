package com.app.ecom;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<User> userList = new ArrayList<>();

    public List<User> getAllUsers(){
        return userList;
    }
}

package com.app.ecom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<User> userList = new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userList;
    }
    @GetMapping("/api/users")
    public List<User> createUser(@RequestBody User user){
        userList.add(user);
        System.out.println(userList);
        return userList;
    }


}
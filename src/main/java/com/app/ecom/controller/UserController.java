package com.app.ecom.controller;

import com.app.ecom.EcomApplication;
import com.app.ecom.dto.userRequest;
import com.app.ecom.dto.userResponse;
import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/users")
    public List<userResponse> getAllUsers(){
        return userService.fetchAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
       return userService.fetchUser(id)
               .map(ResponseEntity:: ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody userRequest userRequest){
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable  Long id,@RequestBody User updatedUser){
        userService.updateUser(id,updatedUser);
        return ResponseEntity.ok("User added successfully");
    }



}
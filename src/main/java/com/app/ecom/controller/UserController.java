package com.app.ecom.controller;

import com.app.ecom.EcomApplication;
import com.app.ecom.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private Long nextId = 1L;
    private final EcomApplication.UserService userService;

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userService.fetchAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
       return userService.fetchUser(id)
               .map(ResponseEntity:: ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setId(nextId++);
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable  Long id,@RequestBody User updatedUser){
        updatedUser.setId(nextId++);
        userService.addUser(updatedUser);
        return ResponseEntity.ok("User added successfully");
    }



}
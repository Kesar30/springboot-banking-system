package org.example.bankingsystem.controller;

import jakarta.validation.Valid;
import org.example.bankingsystem.requests.LoginRequest;
import org.example.bankingsystem.requests.UserRequest;
import org.example.bankingsystem.services.UserService;
import org.example.bankingsystem.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //post method to add user
    @PostMapping("/users")
    public ResponseEntity<User> add(@Valid @RequestBody UserRequest request){
            User user = userService.addUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    //get method to show user data by id
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest);
        return ResponseEntity.ok(user); // 200 OK with user data
        //without exception Handling
//        if (user != null) {
//            if (user.getPassword().equals(loginRequest.getPassword())) {
//                return ResponseEntity.ok(user); // 200 OK with user data
//            }
//            else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body("Invalid password");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("User not found");
//        }
    }
    //update user detail
    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody UserRequest request,@PathVariable("id") Integer uid ){
            User updatedUser = userService.updateUser(request, uid);
            return  ResponseEntity.ok().body(updatedUser);

    }
    //delete user data
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer uid){
            userService.deleteUser(uid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

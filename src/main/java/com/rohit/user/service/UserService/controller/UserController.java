package com.rohit.user.service.UserService.controller;


import com.rohit.user.service.UserService.entities.User;
import com.rohit.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController

{
    @Autowired
    private UserService userService;
    //Create user


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {

       User user1= userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //Single get user

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {

        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //all user get

    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    //delete user by id

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteSingleUser(@PathVariable String userId)
    {
        String user = userService.deleteUser(userId);
        return ResponseEntity.ok(user);

    }

    // update user

//    @PutMapping("/updateUser/{userId}")
//    public String updateSingleUser(@PathVariable String userId,@RequestBody String name)
//    {
//        return userService.updateUser(userId,name);
//    }
}

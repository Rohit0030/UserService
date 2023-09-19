package com.rohit.user.service.UserService.services;

import com.rohit.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //user operation


    // create

    User saveUser(User user);


    //get All Users
    List<User> getAllUser();


    // get single user of given userID

    User getUser(String userID);

    // delete single user by userID

    String deleteUser(String UserID);

    //Update user by id
//    String updateUser(String userId, String name);







}

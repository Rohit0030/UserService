package com.rohit.user.service.UserService.services.impl;

import com.rohit.user.service.UserService.entities.User;
import com.rohit.user.service.UserService.exception.ResourceNotFoundException;
import com.rohit.user.service.UserService.repositories.UserRepository;
import com.rohit.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {

        //generate unique ID 22 and 23 line number
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userID) {
        return userRepository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on Service!!!!!!"+userID));
    }

    @Override
    public String deleteUser(String userID) {
         userRepository.deleteById(userID);
        return " 👍 Successfully deleted User: "+userID;
    }

//    @Override
//    public String updateUser(String userId, String name) {
//        User user = userRepository.getReferenceById(userId);
//        user.setName(name);
//        userRepository.save(user);
//        return "Successfully  name updated on Server !!!!!!! ThankYou ❤️";
//
//    }
}

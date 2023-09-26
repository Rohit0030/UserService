package com.rohit.user.service.UserService.services.impl;

import com.rohit.user.service.UserService.entities.Hotel;
import com.rohit.user.service.UserService.entities.Rating;
import com.rohit.user.service.UserService.entities.User;
import com.rohit.user.service.UserService.exception.ResourceNotFoundException;
import com.rohit.user.service.UserService.external.service.HotelService;
import com.rohit.user.service.UserService.repositories.UserRepository;
import com.rohit.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

  //  private Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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

    //

    @Override
    public User getUser(String userID) {
        //get user from database with the help of user repository
        User user=  userRepository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("🫤🫤🫤 User with given id is not found on Server!!!!!! "+userID));

        // fetch rating of the above user from RATING SERVICE
        //http://localhost:8083/ratings/users/ab4bef2f-b44d-4ec5-9621-c3e8d1795361
       Rating[] ratingsOfUser= restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userID, Rating[].class);
        logger.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        //for hotel coding
        List<Rating> ratingList = ratings.stream().map(rating -> {
           //Api call to hotel service to get the hotel
            //first method using restTemplate
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            //Second Method using Feign Client
       Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("response status code: {} ",forEntity.getStatusCode());
            //set a hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;

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

package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Client_Profile_Private_Service {

    @Autowired
    UserRepository userRepository;

    public User getUser(){
        User user = userRepository.findById(1).get();
        return user;
    }



}

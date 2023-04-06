package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.services.Client_Profile_Private_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
public class MainController {

    @RequestMapping(path = "/home")
    public String homepage() {
        return "HomePage.html";
    }
    @RequestMapping(path = "/header")
    public String header() {
        return "Header.html";
    }

    @RequestMapping(path = "/profile")
    public String userProfile() {
        return "User_Profile.html";
    }


    @Autowired
    Client_Profile_Private_Service clientProfilePrivateService;

    @GetMapping("/private")
    public String specialistPrivateProfile(Model model){
        User user = clientProfilePrivateService.getUser();
        model.addAttribute("userProfile", user);
        return "Specialist_Profile_Private.html";
    }
    @GetMapping("/private/edit")
    public String specialistPrivateEdit(Model model){
        User user = clientProfilePrivateService.getUser();
        model.addAttribute("userProfile", user);
        return "Specialist_Profile_Edit.html";
    }

    @GetMapping("/clientPrivate")
    public String clientPrivateProfile(Model model){
        User user = clientProfilePrivateService.getUser();
        model.addAttribute("userProfile", user);
        return "Client_Profile_Private.html";
    }

    @GetMapping("/clientPrivate/edit")
    public String clientPrivateEdit(Model model){
        User user = clientProfilePrivateService.getUser();
        model.addAttribute("userProfile", user);
        return "Client_Profile_Edit.html";
    }


    @RequestMapping(path = "/payment")
    public String payment() {
        return "Payment.html";
    }

    @RequestMapping(path = "/registration")
    public String register() {
        return "Registration_Two_Options.html";
    }




}


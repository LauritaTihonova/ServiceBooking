package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.Role;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.repositories.UserRepository;
import com.project.ServiceBooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    UserService userService;

    @GetMapping("/private")
    public String specialistPrivateProfile(Model model){
        User user = userService.findById(908); // in future should be changed!!!!
        model.addAttribute("userProfile", user);
        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Private.html";
        }
        else{
            return "Specialist_Profile_Private.html";
        }
    }
    @GetMapping("/private/edit") // display an edit page
    public String privateEditPage(Model model){
        User user = userService.findById(908);
        model.addAttribute("userProfile", user); // here I 'push' in user object into html code
        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Edit.html";
        }
        else{
            return "Specialist_Profile_Edit.html";
        }
    }

    @PostMapping("/private/edit")
    public ModelAndView privateEdited(@ModelAttribute User user, ModelMap model){
        userService.saveUser(user);
        model.addAttribute("userProfile", user);
        return new ModelAndView("redirect:/private", model);
    }
///
//    @Autowired
//    UserRepository userRepository;
//    @GetMapping("/private/editUser")
//    public ModelAndView showPrivateEdit(){
//        User user = userRepository.findById(403).get();
//        ModelAndView mav = new ModelAndView("Client_Profile_Edit.html");
//        mav.addObject("userProfile", user);
//        return mav;
//    }



    @RequestMapping(path = "/payment")
    public String payment() {
        return "Payment.html";
    }

    @RequestMapping(path = "/registration")
    public String register() {
        return "Registration_Two_Options.html";
    }




}


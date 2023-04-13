package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.*;


import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;

import com.project.ServiceBooking.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



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
// THIS WILL COULD BE USED FOR FETCHING PERSONAL PAGE DEPENDING ON WHO IS ACTUALLY LOGGED IN:
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        User user = userService.findByEnterEmail(currentUserName);

        User user = userService.findById(7);
        Set<Language> languages = user.getLanguages();
        model.addAttribute("userProfile", user);
        model.addAttribute( "languages", languages);

        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Private.html";
        }
        else{
            return "Specialist_Profile_Private.html";
        }
    }
    @GetMapping("/private/edit") // display an edit page
    public String privateEditPage(Model model){
        // THIS WILL COULD BE USED FOR FETCHING PERSONAL PAGE DEPENDING ON WHO IS ACTUALLY LOGGED IN:
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        User user = userService.findByEnterEmail(currentUserName);

        User user = userService.findById(7);
        Set<Language> languages = user.getLanguages();
        model.addAttribute("userProfile", user); // here I 'push' in user object into html code
        model.addAttribute( "languages", languages);

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

    @RequestMapping(path = "/payment")
    public String payment() {
        return "Payment.html";
    }

    @RequestMapping(path = "/registration")
    public String register() {
        return "Registration_Two_Options.html";
    }

    @RequestMapping(path = "/about-us")
    public String aboutUs() {
        return "about-us.html";
    }

    @RequestMapping(path = "/contact-us")
    public String contactUs() {
        return "contact-us.html";
    }


    @Autowired
    ServicesCategoryService servicesCategoryService;

    @GetMapping("/services")
    public String listAllServices(Model model) {
        List<ServicesCategory> servicesCategories = servicesCategoryService.getAllServices();
        model.addAttribute("servicesCategories", servicesCategories);
        return "ServiceCategoryList.html";
    }

    @Autowired
    ServicesService servicesService;
    @RequestMapping(path = "/services/description/{id}")
    public String description (@PathVariable("id") Integer id, Model model) {
        Service service = servicesService.findById(id);
        model.addAttribute("service", service);
        return "ServiceDescription.html";
    }
}


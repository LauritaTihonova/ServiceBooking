package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Role;
import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.ServicesCategory;
import com.project.ServiceBooking.data.User;


import com.project.ServiceBooking.repositories.UserRepository;
import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;

import com.project.ServiceBooking.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.security.Principal;



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
        return "Specialist_Profile_TEST.html";
    }

    @Autowired
    UserService userService;

    @GetMapping("/private")
    public String specialistPrivateProfile(Model model){
        User user = userService.findById(8); // in future should be changed!!!!
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
        User user = userService.findById(8);
        model.addAttribute("userProfile", user); // here I 'push' in user object into html code
        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Edit.html";
        }
        else{
            return "Specialist_Profile_Edit.html";
        }
    }

    @Autowired
    private UserRepository userRepository;



//
//    @GetMapping("/clientPrivate")
//    public String clientPrivateProfile(Model model){
//        User user = clientProfilePrivateService.getUser();
//        model.addAttribute("userProfile", user);
//        return "Client_Profile_Private.html";
//    }
//
//    @GetMapping("/clientPrivate/edit")
//    public String clientPrivateEdit(Model model){
//        User user = clientProfilePrivateService.getUser();
//        model.addAttribute("userProfile", user);
//        return "Client_Profile_Edit.html";
//    }


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

    @RequestMapping(path = "/about-us")
    public String aboutUs() {
        return "about-us.html";
    }

    @RequestMapping(path = "/contact-us")
    public String contactUs() {
        return "contact-us.html";
    }

    @RequestMapping(path = "contact-us/submit")
    public String submitForm() {return "submit.html";}



    @Autowired
    ServicesCategoryService servicesCategoryService;
    @GetMapping("/services")
    public String getServiceCategories(Model model) {
        List<ServicesCategory> serviceCategories = servicesCategoryService.getAllServices();

        Map<String, Set<String>> categoriesMap = new HashMap<>();
        for (ServicesCategory category : serviceCategories) {
            String categoryName = category.getCategory();
            String subCategory = category.getSubCategory();

            if (!categoriesMap.containsKey(categoryName)) {
                categoriesMap.put(categoryName, new HashSet<>());
            }
            categoriesMap.get(categoryName).add(subCategory);
        }

        model.addAttribute("categoriesMap", categoriesMap);
        return "ServiceCategoryList.html";
    }


    @Autowired
    ServicesService servicesService;
    @RequestMapping(path = "/services/services")
    public String serviceList(@RequestParam("subCategory") String subCategory, Model model) {
        List<Service> services = servicesService.findBySubCategory(subCategory);
        model.addAttribute("services", services);
        return "ServiceList.html";
    }

    @GetMapping("/specialist/{userId}")
    public String getSpecialistProfile(@PathVariable Integer userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "Specialist_Profile_TEST.html";
    }

}


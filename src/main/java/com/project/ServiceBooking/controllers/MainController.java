package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Role;
import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.ServicesCategory;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.services.Client_Profile_Private_Service;
import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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
        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Private.html";
        }
        else{
            return "Specialist_Profile_Private.html";
        }
    }
    @GetMapping("/private/edit")
    public String specialistPrivateEdit(Model model){
        User user = clientProfilePrivateService.getUser();
        model.addAttribute("userProfile", user);
        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Edit.html";
        }
        else{
            return "Specialist_Profile_Edit.html";
        }
    }
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


    @RequestMapping(path = "/payment")
    public String payment() {
        return "Payment.html";
    }

    @RequestMapping(path = "/registration")
    public String register() {
        return "Registration_Two_Options.html";
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


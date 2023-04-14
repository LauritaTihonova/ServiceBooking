package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.*;


import com.project.ServiceBooking.services.LanguageService;
import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;

import com.project.ServiceBooking.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    LanguageService languageService;
    @GetMapping("/private")
    public String privateProfile(Model model){
// THIS WILL COULD BE USED FOR FETCHING PERSONAL PAGE DEPENDING ON WHO IS ACTUALLY LOGGED IN:
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        User user = userService.findByEnterEmail(currentUserName);

        User user = userService.findById(7);
        ArrayList<Language> languages = (ArrayList<Language>)languageService.findByUser(user.getId()); // I'm fetching languages separately from user

        PrivateEditForm editForm = new PrivateEditForm();
        editForm.setUser(user);
        editForm.setLanguageList(languages);

        model.addAttribute("wrapper", editForm);

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
        ArrayList<Language> languages = (ArrayList<Language>)languageService.findByUser(user.getId()); // because this is fetched separately then it should probably be saved separately as well

        PrivateEditForm editForm = new PrivateEditForm();
        editForm.setUser(user);
        editForm.setLanguageList(languages);

        model.addAttribute("wrapper", editForm);

        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Edit.html";
        }
        else{
            return "Specialist_Profile_Edit.html";
        }
    }

    //this will execute after pressing save button:
    @PostMapping("/private/edit")
    public ModelAndView privateEdited(@ModelAttribute("userProfile") PrivateEditForm editForm, ModelMap model){
        userService.saveUser(editForm.user);
        languageService.save((ArrayList<Language>)editForm.languageList);

        model.addAttribute("wrapper", editForm);

        return new ModelAndView("redirect:/private", model);
    }

    //for deleting separate languages
    @GetMapping("/private/edit/deleteLang")
    public String deleteLanguage(@RequestParam Integer languageId){
        languageService.deleteById(languageId);
        return "redirect:/private/edit";
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

    @RequestMapping(path = "contact-us/submit")
    public String submitForm() {return "submit.html";}
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


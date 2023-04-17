package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.*;


import com.project.ServiceBooking.repositories.AccountRepository;
import com.project.ServiceBooking.services.*;

import com.project.ServiceBooking.services.LanguageService;
import com.project.ServiceBooking.services.SkillService;
import com.project.ServiceBooking.services.EducationService;


import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;

import com.project.ServiceBooking.services.UserService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.*;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    @Autowired
    LanguageService languageService;
    @Autowired
    SkillService skillService;
    @Autowired
    EducationService educationService;

    @GetMapping("/private")
    public String privateProfile(Model model){
        // THIS IS USED FOR FETCHING PERSONAL PAGE DEPENDING ON WHO IS ACTUALLY LOGGED IN:
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        User user = userService.findByEnterEmail(currentUserName);

        User user = userService.findById(8);
        ArrayList<Language> languages = (ArrayList<Language>)languageService.findByUser(user.getId()); // I'm fetching languages separately from user

        PrivateEditForm editForm = new PrivateEditForm();
        editForm.setUser(user);
        editForm.setLanguageList(languages);

        if(user.getRole() == Role.SPECIALIST){
            ArrayList<Skill> skills = (ArrayList<Skill>)skillService.findBySeller(user.getSellerIdSeller().getId());
            ArrayList<Education> educations = (ArrayList<Education>)educationService.findBySeller(user.getSellerIdSeller().getId());
            ArrayList<Service> services = (ArrayList<Service>)servicesService.findByUserId(user.getId());
            editForm.setSkillList(skills);
            editForm.setEducationList(educations);
            editForm.setServiceList(services);
        }

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
        // THIS IS USED FOR FETCHING PERSONAL PAGE DEPENDING ON WHO IS ACTUALLY LOGGED IN:
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        User user = userService.findByEnterEmail(currentUserName);

        User user = userService.findById(8);
        ArrayList<Language> languages = (ArrayList<Language>)languageService.findByUser(user.getId()); // because this is fetched separately then it should probably be saved separately as well

        PrivateEditForm editForm = new PrivateEditForm();
        editForm.setUser(user);
        editForm.setLanguageList(languages);

        if(user.getRole() == Role.SPECIALIST){
            ArrayList<Skill> skills = (ArrayList<Skill>)skillService.findBySeller(user.getSellerIdSeller().getId());
            ArrayList<Education> educations = (ArrayList<Education>)educationService.findBySeller(user.getSellerIdSeller().getId());
            ArrayList<Service> services = (ArrayList<Service>)servicesService.findByUserId(user.getId());
            editForm.setSkillList(skills);
            editForm.setEducationList(educations);
            editForm.setServiceList(services);
        }
        model.addAttribute("wrapper", editForm);

        if(user.getRole() == Role.CLIENT){
            return "Client_Profile_Edit.html";
        }
        else{
            return "Specialist_Profile_Edit.html";
        }
    }

    //this will execute after pressing save button:
    @PostMapping(value = "/private/edit")
    public ModelAndView privateEdited(@ModelAttribute("wrapper") PrivateEditForm editForm, ModelMap model){
        userService.saveUser(editForm.user);
        languageService.save((ArrayList<Language>)editForm.languageList);
        if(editForm.user.getRole() == Role.SPECIALIST){
            skillService.save((ArrayList<Skill>) editForm.skillList);
            educationService.save((ArrayList<Education>) editForm.educationList);
        }

        model.addAttribute("wrapper", editForm);

        return new ModelAndView("redirect:/private", model);
    }

    //for deleting separate languages
    @GetMapping("/private/edit/deleteLang")
    public String deleteLanguage(@RequestParam Integer languageId){
        languageService.deleteById(languageId);
        return "redirect:/private/edit";
    }

    @PostMapping(value = "/private/edit/addLang")
    public ModelAndView addLang(@ModelAttribute("wrapper") PrivateEditForm editForm, ModelMap model){
        userService.saveUser(editForm.user);
        languageService.save((ArrayList<Language>)editForm.languageList);
        Language languageToAdd = new Language();
        languageToAdd.setLanguage(editForm.newLanguage);
        languageToAdd.setIdUsers(editForm.user);
        languageService.saveOne(languageToAdd);

        model.addAttribute("wrapper", editForm);

        return new ModelAndView("redirect:/private/edit", model);
    }

    @GetMapping("/private/edit/deleteSkill")
    public String deleteSkill(@RequestParam Integer skillId){
        skillService.deleteById(skillId);
        return "redirect:/private/edit";
    }

    @PostMapping(value = "/private/edit/addSkill")
    public ModelAndView addSkill(@ModelAttribute("wrapper") PrivateEditForm editForm, ModelMap model){
        userService.saveUser(editForm.user);
        languageService.save((ArrayList<Language>)editForm.languageList);
        skillService.save((ArrayList<Skill>) editForm.skillList);
        educationService.save((ArrayList<Education>) editForm.educationList);

        Skill skillToAdd = new Skill();
        skillToAdd.setSkill(editForm.newSkill);
        skillToAdd.setIdSeller(editForm.user.getSellerIdSeller());

        skillService.saveOne(skillToAdd);

        model.addAttribute("wrapper", editForm);

        return new ModelAndView("redirect:/private/edit", model);
    }

    @GetMapping("/private/edit/deleteEducation")
    public String deleteEducation(@RequestParam Integer educationId){
        educationService.deleteById(educationId);
        return "redirect:/private/edit";
    }

    @PostMapping(value = "/private/edit/addEducation")
    public ModelAndView addEducation(@ModelAttribute("wrapper") PrivateEditForm editForm, ModelMap model){
        userService.saveUser(editForm.user);
        languageService.save((ArrayList<Language>)editForm.languageList);
        skillService.save((ArrayList<Skill>) editForm.skillList);
        educationService.save((ArrayList<Education>) editForm.educationList);

        Education educationToAdd = new Education();
        educationToAdd.setEducation(editForm.newEducation);
        educationToAdd.setIdSeller(editForm.user.getSellerIdSeller());

        educationService.saveOne(educationToAdd);

        model.addAttribute("wrapper", editForm);

        return new ModelAndView("redirect:/private/edit", model);
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


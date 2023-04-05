package com.project.ServiceBooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {

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

        @GetMapping("/private")
        public String specialistPrivateProfile(){
            return "Specialist_Profile_Private.html";
        }
        @GetMapping("/private/edit")
        public String specialistPrivateEdit(){
            return "Specialist_Profile_Edit.html";
        }

        @GetMapping("/clientPrivate")
        public String clientPrivateProfile(){
            return "Client_Profile_Private.html";
        }

        @GetMapping("/clientPrivate/edit")
        public String clientPrivateEdit(){
            return "Client_Profile_Edit.html";
        }



}


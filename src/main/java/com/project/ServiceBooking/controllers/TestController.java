package com.project.ServiceBooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {
    @RequestMapping(path = "/home")
    public String homepage() {
        return "HomePage.html";
    }

}


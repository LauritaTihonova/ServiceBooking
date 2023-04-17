package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.UserRepository;
import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;
import com.project.ServiceBooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.Authentication;

import com.project.ServiceBooking.data.Booking;
import com.project.ServiceBooking.service.BookingService;
import com.project.ServiceBooking.service.UserService;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.services.BookingService;



import java.time.Instant;
import java.util.*;
import java.security.Principal;

@Controller
public class BookingController {

    @Autowired
    ServicesService servicesService;
    @Autowired
    ServicesCategoryService servicesCategoryService;
    @Autowired
    UserService userService;

    @Autowired
    private BookingService bookingService;


    @PostMapping("/bookings/create")
    public String createBooking(@ModelAttribute("booking") Booking booking, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEnterEmail(userDetails.getUsername());
        booking.setClient(user);
        bookingService.save(booking);
        return "redirect:/bookings";
    }

}

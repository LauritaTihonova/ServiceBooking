package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.*;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.security.Principal;

@Controller
public class BookingController {

    @Autowired
    ServicesService servicesService;
    @Autowired
    ServicesCategoryService servicesCategoryService;

    @GetMapping("/booking/{serviceId}")
    public String getBookingForm(@PathVariable int serviceId, Model model) {
        Service service = servicesService.getServiceById(serviceId);
        List<ServicesCategory> serviceCategories = servicesCategoryService.getAllServices();
        Booking booking = new Booking();
        booking.setService(service);
        model.addAttribute("booking", booking);
        model.addAttribute("serviceCategories", serviceCategories);

        return "booking-form";
    }


    @PostMapping("/booking")
    public String processBookingForm(Model model, @RequestParam Map<String, String> params) {
        return "redirect:/booking/confirmation.html";
    }

    @GetMapping("/booking/confirmation")
    public String showBookingConfirmation(Model model) {
        return "confirmation.html";
    }

    public String getScriptCode() {
        String scriptCode =
                "// Open the booking modal when the \"Contact\" button is clicked\n" +
                        "document.getElementById(\"contactButton\").addEventListener(\"click\", function() {\n" +
                        "    document.getElementById(\"bookingModal\").style.display = \"block\";\n" +
                        "});\n\n" +
                        "// Close the booking modal when the \"x\" button is clicked\n" +
                        "document.getElementsByClassName(\"close\")[0].addEventListener(\"click\", function() {\n" +
                        "    document.getElementById(\"bookingModal\").style.display = \"none\";\n" +
                        "});\n\n" +
                        "// Initialize the calendar\n" +
                        "flatpickr(\"#calendar\", {\n" +
                        "    enableTime: false,\n" +
                        "    dateFormat: \"Y-m-d\",\n" +
                        "    minDate: \"today\",\n" +
                        "    maxDate: new Date().fp_incr(30), // Allow bookings up to 30 days in advance\n" +
                        "    disable: [\n" +
                        "        // Disable weekends\n" +
                        "        function(date) {\n" +
                        "            return date.getDay() === 0 || date.getDay() === 6;\n" +
                        "        }\n" +
                        "    ]\n" +
                        "});\n\n" +
                        "// Submit the booking form when it's submitted\n" +
                        "document.getElementById(\"bookingForm\").addEventListener(\"submit\", function(event) {\n" +
                        "    event.preventDefault();\n" +
                        "    // Add code to submit the form and send the booking request to the server\n" +
                        "    // You can use AJAX to send the request without refreshing the page\n" +
                        "    // After the request is sent, close the booking modal and display a confirmation message to the user\n" +
                        "    // You can also add error handling code to display an error message if the request fails\n" +
                        "});\n";
        return scriptCode;
    }
}

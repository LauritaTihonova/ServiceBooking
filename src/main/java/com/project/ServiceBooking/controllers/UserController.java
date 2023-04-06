package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Status;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.data.Role;
import com.project.ServiceBooking.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/process_register")
    public String addUser(User user, Model model) {
        User userFromDb = userService.findByEnterEmail(user.getEmail());
        if (userFromDb == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRole(Role.CLIENT);
            user.setStatus(Status.ACTIVE);
            userService.saveUser(user);
            return "/login";
        }else{
            model.addAttribute("UserAlreadyExist", true);
            return "/registration";
        }
    }

    @PreAuthorize("hasAuthority('specialist:write')")
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "listUsers";
    }

    @GetMapping("/index")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model){
        userService.deleteById(id);
        return "registration";
    }
}

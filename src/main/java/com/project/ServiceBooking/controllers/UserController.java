package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.services.EmailService;
import com.project.ServiceBooking.services.UserService;
import com.project.ServiceBooking.services.VerificationTokenService;
import jakarta.mail.MessagingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
>>>>>>> 0e75f5bca6784a7e0ebf96dac77843946e92695b
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private EmailService emailService;

    public UserController(UserService userService, VerificationTokenService verificationTokenService, EmailService emailService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    com.project.ServiceBooking.data.Role myRole = com.project.ServiceBooking.data.Role.CLIENT;

    @PostMapping("/process_register")
    public String addUser(User user, Model model) throws MessagingException {
        User userFromDb = userService.findByEnterEmail(user.getEmail());
<<<<<<< HEAD
        if (userFromDb == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRole(Role.CLIENT);
//          user.setStatus(Status.ACTIVE);
            userService.saveUser(user);
            return "/login";
=======
        if(userFromDb == null) {
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = passwordEncoder.encode(user.getPassword());
//            user.setPassword(encodedPassword);
//            user.setRole(Role.CLIENT);
//            user.setStatus(Status.INACTIVE);
//            userService.saveUser(user);
            userService.saveClient(user);
            //Verification
            // create token
//            String token = UUID.randomUUID().toString();
//            verificationTokenService.save(user, token);
//            //send verification email
//            emailService.sendHtmlMail(user);
            model.addAttribute("message","A verification email has been sent ti your email address.");
>>>>>>> 0e75f5bca6784a7e0ebf96dac77843946e92695b
        }else{
            //model.addAttribute("UserAlreadyExist", true);
            model.addAttribute("message","User already exist.");
        }
        return "/login";
    }

    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model) throws MessagingException {
        //create html page activation
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if(verificationToken==null){
            model.addAttribute("message","Your verification token is invalid.");
        }else{
            User user=verificationToken.getUser();
            //if the user account is not activated
            //Status userStatus=user.getStatus();
            if(user.getStatus()!=Status.ACTIVE){
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                //check if the token os expired
                if(verificationToken.getExpiryDate().before(currentTimestamp)){
                    model.addAttribute("message", "Your verification token has expired. We send new email with activation link.");
                    //add delete token and create new
                    verificationTokenService.deleteById(verificationToken.getTokenId());
                    String token_new = UUID.randomUUID().toString();
                    verificationTokenService.save(user, token_new);
                    emailService.sendHtmlMail(user);
                }else{
                    //the token is valid, active the user account
                    user.setStatus(Status.ACTIVE);
                    //update user
                    userService.saveUser(user);
                    model.addAttribute("message","Your account is successfully activated");
                }
            }else {
                //the user account is already activated
                model.addAttribute("message","Your account is already activated");
            }
        }

        return "activation";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model){

        User user=userService.findById(id);
        model.addAttribute("user", user);
        return "/updateUser";
    }
    @PostMapping("/updateUser")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/auth/users";
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

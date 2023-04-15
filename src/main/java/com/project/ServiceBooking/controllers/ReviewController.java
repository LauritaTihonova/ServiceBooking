package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Review;
import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.services.ReviewService;
import com.project.ServiceBooking.services.ServicesService;
import com.project.ServiceBooking.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final ServicesService servicesService;

    public ReviewController(UserService userService, ReviewService reviewService, ServicesService servicesService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.servicesService = servicesService;
    }

//    @GetMapping("/createReview")
//    public String createReviewForm(Model model, HttpServletRequest request, Integer id){
//        model.addAttribute("review", new Review());
//        String previousUrl = request.getHeader("Referer");
//        model.addAttribute("previousUrl", previousUrl);
//        Service service=servicesService.findById(id);
//        model.addAttribute("service",service);
//        return "createReview";
//    }
//    @PostMapping("/createReview")
//    public String createReview(@ModelAttribute("previousUrl") String previousUrl, Review review, Integer id){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        //Service service=servicesService.findById(id);
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            User user = userService.findByEnterEmail(currentUserName);
//            review.setUsersIdUsers(user);
//            //review.setIdServices(service);
//            reviewService.saveReview(review);
//        }
//        return "redirect:"+previousUrl;
//    }
    @PostMapping ("/createReview")
    private String createReview(Integer id, Integer rating, String TextArea, Review review, HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Service service=servicesService.findById(id);
        String previousUrl = request.getHeader("Referer");
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userService.findByEnterEmail(currentUserName);
            review.setComment(TextArea);
            review.setRating(rating);
            review.setUsersIdUsers(user);
            review.setIdServices(service);
            reviewService.saveReview(review);
            service.setCountreview(reviewService.findByidServices(id).size());
            service.setAvgrating(reviewService.findAvgRating(id));
            servicesService.saveService(service);
        }
        return "redirect:"+previousUrl;
    }
}

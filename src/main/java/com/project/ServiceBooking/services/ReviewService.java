package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Review;
import com.project.ServiceBooking.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review findById(Integer id){
        return reviewRepository.findById(id).orElse(null);
    }
    public List<Review> findAll(){
        return reviewRepository.findAll();
    }
    public Review saveReview(Review review){
        return reviewRepository.save(review);
    }

    public List<Review> findByidServices(Integer idServices) {
        return reviewRepository.findByidServices(idServices);
    }

    public void deleteById(Integer id){
        reviewRepository.deleteById(id);
    }
}

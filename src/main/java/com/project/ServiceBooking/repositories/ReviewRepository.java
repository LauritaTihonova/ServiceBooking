package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

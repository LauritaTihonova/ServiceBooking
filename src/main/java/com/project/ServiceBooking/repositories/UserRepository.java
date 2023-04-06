package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

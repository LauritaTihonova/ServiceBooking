package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a JOIN a.users u WHERE u.email = :email")
    List<Account> findByEmail(@Param("email") String email);

}


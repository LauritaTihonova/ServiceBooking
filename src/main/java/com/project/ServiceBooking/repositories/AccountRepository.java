package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a JOIN a.users u WHERE u.email = :email")
    Account findByEmail(@Param("email") String email);

    @Query("SELECT a FROM Account a JOIN a.users u WHERE u.password = :password")
    Account findByPassword(@Param("password") String password);


}




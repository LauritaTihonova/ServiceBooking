package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    public Account getAccountId (Integer id) {return accountRepository.findById(id).orElse(null);}
}

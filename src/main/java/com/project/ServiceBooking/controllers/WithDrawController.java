package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WithDrawController {

    @Autowired
    AccountRepository accountRepository;
    @PostMapping("/withdraw")
    public String withdrawMoney(@RequestParam String password,  @RequestParam Integer amount, Model model) {
        // Find account by email
        Account account = accountRepository.findByPassword(password);


        // Check if there's enough balance to withdraw
        if (account.getMoney() < amount) {
            model.addAttribute("error", "Insufficient balance!");
            return "withdraw.html";
        }

        // Update account balance
        Integer newBalance = account.getMoney() - amount;
        account.setMoney(newBalance);
        accountRepository.save(account);

        // Show success message
        model.addAttribute("success", "Withdrawal successful! New balance: " + newBalance);
        return "withdraw.html";
    }
}

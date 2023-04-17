package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Payment;
import com.project.ServiceBooking.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public Payment findById (Integer id) {
        return paymentRepository.findById(id).orElse(null);
    }
}

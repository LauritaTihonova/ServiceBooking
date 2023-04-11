package com.project.ServiceBooking.services;

import com.project.ServiceBooking.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {

    @Autowired
    ServiceRepository serviceRepository;

    public com.project.ServiceBooking.data.Service findById(Integer id) {
    return serviceRepository.findById(id).orElse(null);
    }
}


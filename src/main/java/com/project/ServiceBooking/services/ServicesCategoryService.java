package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.ServicesCategory;
import com.project.ServiceBooking.repositories.ServicesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesCategoryService {

    @Autowired
    ServicesCategoryRepository servicesCategoryRepository;

    public List<ServicesCategory> getAllServices() {
        return servicesCategoryRepository.findAll();
    }

/*    public ServicesCategory getServices() {
        ServicesCategory servicesCategory = servicesCategoryRepository.findById(1).get();
        return servicesCategory;
    }*/
}

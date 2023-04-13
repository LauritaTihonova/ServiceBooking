package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.Subcategory;
import com.project.ServiceBooking.repositories.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ServicesService {

    @Autowired
    ServiceRepository serviceRepository;

    public Service findById(Integer id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public List<Service> findAllService(){
        return serviceRepository.findAll();
    }

    public Service saveService(Service service){
        return serviceRepository.save(service);
    }

    public void deleteById(Integer id){
        serviceRepository.deleteById(id);
    }

    public List<Service> findBySubcategory(Subcategory subcategory) {
        return serviceRepository.findBySubcategory(subcategory);
    }

}

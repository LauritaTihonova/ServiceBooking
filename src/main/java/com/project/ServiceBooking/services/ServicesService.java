package com.project.ServiceBooking.services;

import com.project.ServiceBooking.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {

    @Autowired
    ServiceRepository serviceRepository;

    public com.project.ServiceBooking.data.Service findById(Integer id) {
    return serviceRepository.findById(id).orElse(null);
    }

    public List<com.project.ServiceBooking.data.Service> findAllService(){
        return serviceRepository.findAll();
    }
    public com.project.ServiceBooking.data.Service saveService(com.project.ServiceBooking.data.Service service){
        return serviceRepository.save(service);
    }

    public void deleteById(Integer id){
        serviceRepository.deleteById(id);
    }

    public List<com.project.ServiceBooking.data.Service> findBySubCategory(String subCategory) {
        return serviceRepository.findBySubCategory(subCategory);
    }

    public com.project.ServiceBooking.data.Service getServiceById(int id) {
        Optional<com.project.ServiceBooking.data.Service> serviceData = serviceRepository.findById(id);
        if (serviceData.isPresent()) {
            return serviceData.get();
        } else {
            throw new RuntimeException("Service not found with id: " + id);
        }
    }



}


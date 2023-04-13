package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.project.ServiceBooking.data.Subcategory;


public interface ServiceRepository extends JpaRepository<Service, Integer> {

    List<Service> findBySubcategory(Subcategory subcategory);
}



package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT s FROM Service s JOIN s.idServicesCategory c WHERE c.subCategory = :subCategory")
    List<Service> findBySubCategory(@Param("subCategory") String subCategory);
}


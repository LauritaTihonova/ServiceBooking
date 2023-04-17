package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT s FROM Service s JOIN s.idServicesCategory c WHERE c.subCategory = :subCategory")
    List<Service> findBySubCategory(@Param("subCategory") String subCategory);

    @Query(value = "SELECT * FROM services WHERE users_id_users = ?1",
            nativeQuery = true)
    List<Service> findByUserId(Integer userId);
}

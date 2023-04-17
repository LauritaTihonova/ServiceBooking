package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Integer> {

    @Query(value = "SELECT * FROM education WHERE seller_id_seller = ?1",
            nativeQuery = true)
    List<Education> findBySeller(Integer sellerId);
}

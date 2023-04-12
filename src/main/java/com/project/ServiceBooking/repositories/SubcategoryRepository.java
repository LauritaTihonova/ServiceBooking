package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {
}

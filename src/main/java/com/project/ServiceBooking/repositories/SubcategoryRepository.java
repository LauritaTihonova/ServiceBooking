package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {
    List<Subcategory> findByServicesCategoryId(Integer id);
}

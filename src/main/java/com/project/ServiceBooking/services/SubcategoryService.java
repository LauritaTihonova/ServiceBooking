package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Subcategory;
import com.project.ServiceBooking.repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    @Autowired
    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public Optional<Subcategory> findById(Integer id) {
        return subcategoryRepository.findById(id);
    }

    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }

    public Subcategory saveSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public void deleteById(Integer id) {
        subcategoryRepository.deleteById(id);
    }
}

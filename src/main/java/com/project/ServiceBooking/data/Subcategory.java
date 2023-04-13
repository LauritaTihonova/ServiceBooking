package com.project.ServiceBooking.data;

import com.project.ServiceBooking.repositories.SubcategoryRepository;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategory")
    private Integer id_sub;

    @Column(name = "Sub_category")
    private String sub_category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private ServicesCategory servicesCategory;

    // Constructor
    public Subcategory() {}

    @Autowired
    private SubcategoryRepository subcategoryRepository;
    public Subcategory findById(Integer id) {
        return subcategoryRepository.findById(id).orElse(null);
    }

    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

}

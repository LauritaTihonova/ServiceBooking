package com.project.ServiceBooking.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}

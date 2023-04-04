package com.project.ServiceBooking.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "services_category")
public class ServicesCategory {
    @Id
    @Column(name = "id_services_category", nullable = false)
    private Integer id;

    @Column(name = "Category", nullable = false, length = 45)
    private String category;

    @Column(name = "Sub_category", nullable = false, length = 45)
    private String subCategory;

}
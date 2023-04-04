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
@Table(name = "seller")
public class Seller {
    @Id
    @Column(name = "id_seller", nullable = false)
    private Integer id;

    @Column(name = "Skills", length = 1000)
    private String skills;

    @Column(name = "Education", length = 1000)
    private String education;

}
package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "sellerIdSeller")
    private Set<User> users = new LinkedHashSet<>();

}
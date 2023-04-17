package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideducation", nullable = false)
    public Integer id;

    @Column(name = "education_name")
    public String education;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id_seller")
    public Seller idSeller;
}

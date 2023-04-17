package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idskills", nullable = false)
    public Integer id;

    @Column(name = "skill_name")
    public String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id_seller")
    public Seller idSeller;
}

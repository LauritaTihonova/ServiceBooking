package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import org.hibernate.annotations.Cascade;



import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users", nullable = false)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 45)
    private String name;

    @Column(name = "Surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "Email", nullable = false, length = 45)
    private String email;

    @Column(name = "Password", nullable = false, length = 45)
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "Status", nullable = false, length = 45)
    private Status status;

    @Column(name = "Picture",  length = 255)
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_account", nullable = true)
    private Account accountIdaccount;

    @OneToMany(mappedBy = "idUsers", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Language> languages = new LinkedHashSet<>();

    @Column(name = "Description", length = 5000)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "Role", nullable = false, length = 45)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller")
    private Seller sellerIdSeller;

    @OneToMany(mappedBy = "idUsers")
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usersIdUsers")
    private Set<Service> services = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usersIdUsers")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUsers")
    private Set<Booking> bookings = new LinkedHashSet<>();

}
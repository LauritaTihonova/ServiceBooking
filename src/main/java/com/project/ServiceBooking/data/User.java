package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
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

    @Column(name = "Status", nullable = false, length = 45)
    private String status;

    @Column(name = "Picture", nullable = false, length = 45)
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_idaccount", nullable = false)
    private Account accountIdaccount;

    @Column(name = "Language", length = 45)
    private String language;

    @Column(name = "Description", length = 5000)
    private String description;

    @Column(name = "Role", nullable = false, length = 45)
    private String role;

}
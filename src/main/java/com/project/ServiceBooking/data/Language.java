package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlanguage", nullable = false)
    public Integer id;

    @Column(name = "language")
    public String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id_users")
    public User idUsers;

}

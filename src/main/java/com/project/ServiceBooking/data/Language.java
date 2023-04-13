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
    @Column(name = "idlanguage", nullable = false)
    private Integer id;

    @Column(name = "language")
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id_users")
    private User idUsers;

}

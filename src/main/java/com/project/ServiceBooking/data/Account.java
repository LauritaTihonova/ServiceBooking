package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "idaccount", nullable = false)
    private Integer id;

    @Column(name = "money")
    private Integer money;

    @Column(name = "card_number", length = 45)
    private String cardNumber;

    @OneToMany(mappedBy = "accountIdaccount")
    private Set<User> users = new LinkedHashSet<>();

}
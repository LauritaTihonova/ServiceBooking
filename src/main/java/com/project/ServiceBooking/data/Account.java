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
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "idaccount", nullable = false)
    private Integer id;

    @Column(name = "money")
    private Integer money;

    @Column(name = "card_number", length = 45)
    private String cardNumber;

}
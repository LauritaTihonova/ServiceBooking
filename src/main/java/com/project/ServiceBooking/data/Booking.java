package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id_Booking", nullable = false)
    private Integer id;

    @Column(name = "id_seller")
    private Integer idSeller;

    @Column(name = "Date")
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users")
    private User idUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_services")
    private Service idServices;

}
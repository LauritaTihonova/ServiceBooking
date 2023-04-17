package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Booking;
import com.project.ServiceBooking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

}

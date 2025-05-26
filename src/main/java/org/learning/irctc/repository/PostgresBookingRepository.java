package org.learning.irctc.repository;

import org.learning.di.annotation.Component;
import org.learning.di.annotation.Primary;

@Component
@Primary
public class PostgresBookingRepository implements BookingRepository {
    @Override
    public void saveBooking() {
        System.out.println("Postgres BookingRepository: saving booking");
    }
}

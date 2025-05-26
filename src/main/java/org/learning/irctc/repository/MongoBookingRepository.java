package org.learning.irctc.repository;

import org.learning.di.annotation.Component;
import org.learning.di.annotation.Primary;

@Component
public class MongoBookingRepository implements BookingRepository {
    @Override
    public void saveBooking() {
        System.out.println("Booking via mongo...");
    }
}

package org.learning.irctc.repository;

import org.learning.di.annotation.Component;
import org.learning.di.annotation.Property;

@Component
public class MongoBookingRepository implements BookingRepository {
    private final String connectionString;

    public MongoBookingRepository(@Property("db.mongo.connection-uri") String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public void saveBooking() {
        System.out.println("Connecting to MongoDB with connection string: " + connectionString);
        System.out.println("Booking via mongo...");
    }
}

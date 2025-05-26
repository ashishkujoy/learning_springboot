package org.learning.irctc;

import org.learning.di.ApplicationContext;
import org.learning.irctc.service.BookingService;

public class IRCTCApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        BookingService bookingService = applicationContext.getBeat(BookingService.class);

        bookingService.bookTicket("James Bond", "Mumbai", "Delhi");
    }
}

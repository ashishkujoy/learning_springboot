package org.learning.irctc;

import org.learning.di.ApplicationContext;
import org.learning.irctc.service.*;

public class IRCTCApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext();
        BookingService bookingService = applicationContext.get(BookingService.class);
        CancellationService cancellationService = applicationContext.get(CancellationService.class);

        bookingService.bookTicket("user1", "Delhi", "Mumbai");
        cancellationService.cancelTicket(123);
    }
}

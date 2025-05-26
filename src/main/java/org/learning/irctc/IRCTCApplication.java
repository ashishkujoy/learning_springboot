package org.learning.irctc;

import org.learning.di.ApplicationContext;
import org.learning.irctc.repository.BookingRepository;
import org.learning.irctc.service.CancellationService;

public class IRCTCApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        System.out.println("======================================================================");
        BookingRepository bookingRepository = applicationContext.getBeat(BookingRepository.class);
        bookingRepository.saveBooking();
        System.out.println("======================================================================");
        CancellationService cancellationService = applicationContext.getBeat(CancellationService.class);
        if (cancellationService == null) {
            System.out.println("Cancellation service is null");
        } else {
            System.out.println("Cancellation service is not null");
        }
    }
}

package org.learning.irctc;

import org.learning.irctc.gateway.PaymentGateway;
import org.learning.irctc.repository.BookingRepository;
import org.learning.irctc.repository.StationRepository;
import org.learning.irctc.repository.UserRepository;
import org.learning.irctc.service.*;

public class IRCTCApplication {
    public static void main(String[] args) {
        // Manual dependency injection
        StationRepository stationRepository = new StationRepository();
        StationService stationService = new StationService(stationRepository);
        BookingService bookingService = getBookingService(stationService);
        CancellationService cancellationService = new CancellationService(bookingService);

        // Example usage
        stationService.searchStation("Delhi");
        bookingService.bookTicket("user1", "Delhi", "Mumbai");
        cancellationService.cancelTicket(123);
    }

    private static BookingService getBookingService(StationService stationService) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        OfferService offerService = new OfferService();
        DiscountService discountService = new DiscountService(offerService);
        PaymentGateway paymentGateway = new PaymentGateway();
        PaymentService paymentService = new PaymentService(paymentGateway);
        BookingRepository bookingRepository = new BookingRepository();

        return new BookingService(stationService, paymentService, discountService, userService, bookingRepository);
    }
}

package org.learning.irctc.service;

public class CancellationService {
    private final BookingService bookingService;
    public CancellationService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public void cancelTicket(int bookingId) {
        System.out.println("CancellationService: cancelling booking " + bookingId);
        // In a real app, would interact with BookingService/Repository
    }
}

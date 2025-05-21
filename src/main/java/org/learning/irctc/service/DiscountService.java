package org.learning.irctc.service;

public class DiscountService {
    private final OfferService offerService;
    public DiscountService(OfferService offerService) {
        this.offerService = offerService;
    }
    public void applyDiscount() {
        System.out.println("DiscountService: applying discount");
        offerService.applyOffer();
    }
}

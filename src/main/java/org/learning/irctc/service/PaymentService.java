package org.learning.irctc.service;

import org.learning.irctc.gateway.PaymentGateway;

public class PaymentService {
    private final PaymentGateway paymentGateway;
    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public void pay() {
        System.out.println("PaymentService: paying");
        paymentGateway.processPayment();
    }
}

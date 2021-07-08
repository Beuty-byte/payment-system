package service;

import domain.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPaymentsByUserId(int userId);
    Payment getPaymentById(int paymentId);
    List<String> doPayment(long creditCardId, int paymentId);
}

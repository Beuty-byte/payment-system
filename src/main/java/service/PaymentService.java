package service;

import domain.Payment;

import java.util.List;

public interface PaymentService {
    List<domain.Payment> getAllPaymentByUserId(int userId);
    domain.Payment getPaymentById(int paymentId, int userId);
    List<String> doPayment(Payment payment);
}

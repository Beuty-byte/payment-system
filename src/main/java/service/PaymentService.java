package service;

import domain.Payment;

import java.util.List;
import java.util.ResourceBundle;

public interface PaymentService {
    List<Payment> getAllPaymentsByUserId(int userId);
    Payment getPaymentById(int paymentId);
    List<String> doPayment(long creditCardId, int paymentId, ResourceBundle lang);
}

package dao;

import domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {
    List<Payment> getAllPaymentsForUser(int userId);
    Optional<Payment> getPaymentById(int paymentId);
    void deletePayment(Payment payment);
}

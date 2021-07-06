package dao;

import domain.Payment;

import java.util.List;

public interface PaymentDAO {
    List<Payment> getAllCreditsCardWithPayment(int userId);
    Payment getCreditsCardWithPayment(int paymentId, int userId);
    void deletePayment(Payment payment);
}

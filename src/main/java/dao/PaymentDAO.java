package dao;

import domain.Payment;

import java.util.List;
import java.util.Optional;

/**
 * interface for work with payment DAO
 */
public interface PaymentDAO {
    /**
     * returns all payments {@link domain.Payment} for user
     * @param userId user id
     * @return list payments if they there are or else return empty list
     */
    List<Payment> getAllPaymentsForUser(int userId);

    /**
     * return {@link domain.Payment} for user
     * @param paymentId payment id
     * @return payment or else null
     */
    Optional<Payment> getPaymentById(int paymentId);

    /**
     * delete payment
     * @param payment for delete by id
     */
    void deletePayment(Payment payment);
}

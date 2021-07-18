package service;

import domain.Payment;
import java.util.List;
import java.util.ResourceBundle;

/**
 * interface for work with payment servise
 */
public interface PaymentService {
    /**
     * returns list all {@link domain.Payment} for user by id
     * @param userId user id
     * @return list all payments for user or else return empty list
     */
    List<Payment> getAllPaymentsByUserId(int userId);

    /**
     * returns {@link domain.Payment} for user
     * @param paymentId payment id
     * @return payment by id
     * @throws IllegalArgumentException throws if incorrect parameters were sent
     */
    Payment getPaymentById(int paymentId) throws IllegalArgumentException;

    /**
     * returns list errors if impossible to make a payment
     * @param creditCardId credit card id
     * @param paymentId payment id
     * @param lang resource bundle for internationalization error message
     * @return list errors if impossible to make a payment or else return empty list
     */
    List<String> doPayment(long creditCardId, int paymentId, ResourceBundle lang);

}

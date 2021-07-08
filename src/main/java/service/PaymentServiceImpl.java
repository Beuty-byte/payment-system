package service;

import dao.CreditCardDAOImpl;
import dao.PaymentDAOImpl;
import domain.CreditCard;
import domain.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private static final PaymentServiceImpl paymentService = new PaymentServiceImpl();

    public static PaymentServiceImpl getInstance(){
        return paymentService;
    }

    private PaymentServiceImpl() {
    }

    @Override
    public List<Payment> getAllPaymentsByUserId(int userId) {
        return new PaymentDAOImpl().getAllPaymentsForUser(userId);
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        return new PaymentDAOImpl()
                .getPaymentById(paymentId)
                .orElseThrow(EmptyStackException::new);
    }


    @Override
    public List<String> doPayment(long creditCardId, int paymentId) {
        List<String> paymentErrors = new ArrayList<>();
        CreditCard creditCard = new CreditCardDAOImpl()
                .getCreditCardById(creditCardId)
                .orElseThrow(NumberFormatException::new);
        Payment payment = new PaymentDAOImpl()
                .getPaymentById(paymentId).orElseThrow(EmptyStackException::new);
        BigDecimal balance = creditCard.getBankAccount().getBalance();
        BigDecimal paymentSum = payment.getSum();
        BigDecimal difference = balance.subtract(paymentSum);

        if(difference.compareTo(BigDecimal.ZERO) < 0){
            paymentErrors.add("not enough money on the credit card");
        }else if(creditCard.getBankAccount().getBlocked()){
            paymentErrors.add("credit card blocked");
        }else {
            new CreditCardDAOImpl().updateData(paymentSum.negate(),creditCard.getId());
            new PaymentDAOImpl().deletePayment(payment);
        }
        return paymentErrors;
    }
}

package service;

import dao.CreditCardDAOImpl;
import dao.PaymentDAOImpl;
import domain.CreditCard;
import domain.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private static final PaymentServiceImpl paymentService = new PaymentServiceImpl();

    public static PaymentServiceImpl getInstance(){
        return paymentService;
    }

    private PaymentServiceImpl() {
    }

    @Override
    public List<Payment> getAllPaymentByUserId(int userId) {
        return new PaymentDAOImpl().getAllCreditsCardWithPayment(userId);
    }

    @Override
    public Payment getPaymentById(int paymentId, int userId) {
        return new PaymentDAOImpl().getCreditsCardWithPayment(paymentId, userId);
    }


    @Override
    public List<String> doPayment(Payment payment) {
        List<String> paymentErrors = new ArrayList<>();
        CreditCard creditCard = new CreditCardDAOImpl().getCreditCardById(payment.getCreditCardId());
        BigDecimal balance = creditCard.getBankAccount().getBalance();
        BigDecimal paymentSum = payment.getSum();
        BigDecimal difference = balance.subtract(paymentSum);

        System.out.println(paymentSum.negate());
        // кидать эксепшн
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

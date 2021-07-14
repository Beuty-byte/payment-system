package service;

import dao.CreditCardDAOImpl;
import dao.PaymentDAO;
import dao.PaymentDAOImpl;
import domain.CreditCard;
import domain.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentServiceImpl implements PaymentService {

    private static final PaymentServiceImpl paymentService = new PaymentServiceImpl();
    private final PaymentDAO paymentDAO = PaymentDAOImpl.getInstance();

    public static PaymentServiceImpl getInstance(){
        return paymentService;
    }

    private PaymentServiceImpl() {
    }

    @Override
    public List<Payment> getAllPaymentsByUserId(int userId) {
        return paymentDAO.getAllPaymentsForUser(userId);
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        return paymentDAO
                .getPaymentById(paymentId)
                .orElseThrow(EmptyStackException::new);
    }

    @Override
    public List<String> doPayment(long creditCardId, int paymentId, ResourceBundle lang) {
        List<String> paymentErrors = new ArrayList<>();
        CreditCard creditCard = CreditCardDAOImpl.getInstance()
                .getCreditCardById(creditCardId)
                .orElseThrow(NumberFormatException::new);
        Payment payment = PaymentDAOImpl.getInstance()
                .getPaymentById(paymentId).orElseThrow(EmptyStackException::new);
        BigDecimal balance = creditCard.getBankAccount().getBalance();
        BigDecimal paymentSum = payment.getSum();
        BigDecimal difference = balance.subtract(paymentSum);

        if(difference.compareTo(BigDecimal.ZERO) < 0){
            paymentErrors.add(lang.getString("paymentsErrorNotEnoughMoney"));
        }else if(creditCard.getBankAccount().getBlocked()){
            paymentErrors.add(lang.getString("paymentsErrorCreditCardIsBlocked"));
        }else {
            CreditCardDAOImpl.getInstance().updateData(paymentSum.negate(),creditCard.getId());
            PaymentDAOImpl.getInstance().deletePayment(payment);
        }
        return paymentErrors;
    }
}

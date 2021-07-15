package service;

import dao.CreditCardDAO;
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

    private static final PaymentServiceImpl INSTANCE = new PaymentServiceImpl();
    private final PaymentDAO paymentDAO = PaymentDAOImpl.getInstance();
    private final CreditCardDAO creditCardDAO = CreditCardDAOImpl.getInstance();

    public static PaymentServiceImpl getInstance(){
        return INSTANCE;
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
        CreditCard creditCard = creditCardDAO
                .getCreditCardById(creditCardId)
                .orElseThrow(NumberFormatException::new);
        Payment payment = paymentDAO
                .getPaymentById(paymentId).orElseThrow(EmptyStackException::new);
        BigDecimal balance = creditCard.getBankAccount().getBalance();
        BigDecimal paymentSum = payment.getSum();
        BigDecimal difference = balance.subtract(paymentSum);

        if(difference.compareTo(BigDecimal.ZERO) < 0){
            paymentErrors.add(lang.getString("paymentsErrorNotEnoughMoney"));
        }else if(creditCard.getBankAccount().getBlocked()){
            paymentErrors.add(lang.getString("paymentsErrorCreditCardIsBlocked"));
        }else {
            creditCardDAO.updateData(paymentSum.negate(),creditCard.getId());
            paymentDAO.deletePayment(payment);
        }
        return paymentErrors;
    }
}

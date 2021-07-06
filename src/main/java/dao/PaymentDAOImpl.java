package dao;

import domain.Payment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO{
    private Connection connection;

    {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Payment> getAllCreditsCardWithPayment(int userId){
        List<Payment> paymentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.id, p.credit_card_id, p.sum, p.payment_date " +
                "FROM payment p JOIN credit_card cc ON p.credit_card_id = cc.id WHERE cc.users_id = ?")){
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int paymentId = resultSet.getObject("id", Integer.class);
                long creditCardId = resultSet.getLong("credit_card_id");
                BigDecimal paymentSum = resultSet.getBigDecimal("sum");
                Date invoicedPaymentDate = resultSet.getDate("payment_date");
                paymentList.add(new Payment(paymentId, creditCardId, paymentSum, invoicedPaymentDate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paymentList;
    }

    public Payment getCreditsCardWithPayment(int paymentId, int userId){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.id, p.credit_card_id, p.sum, p.payment_date " +
                "FROM payment p JOIN credit_card cc ON p.credit_card_id = cc.id WHERE cc.users_id = ? AND p.id = ?")){
            preparedStatement.setObject(1, userId);
            preparedStatement.setObject(2, paymentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                BigDecimal paymentSum = resultSet.getBigDecimal("sum");
                long creditCardId = resultSet.getLong("credit_card_id");
                Date invoicedPaymentDate = resultSet.getDate("payment_date");
                return new Payment(paymentId,creditCardId, paymentSum, invoicedPaymentDate);
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePayment(Payment payment){
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM payment " +
                "WHERE id = ?")){
            preparedStatement.setObject(1, payment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

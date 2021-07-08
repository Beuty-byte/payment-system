package dao;

import dao.connectionpool.DataSource;
import domain.Payment;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO{
    private Connection connection;
    private static final Logger logger = Logger.getLogger(PaymentDAOImpl.class);

    {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Payment> getAllPaymentsForUser(int userId){
        List<Payment> paymentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, sum, payment_date " +
                "FROM payment  WHERE payment.users_id = ?")){
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int paymentId = resultSet.getInt("id");
                BigDecimal paymentSum = resultSet.getBigDecimal("sum");
                Date invoicedPaymentDate = resultSet.getDate("payment_date");
                paymentList.add(new Payment.Builder()
                        .withId(paymentId)
                        .withPaymentSum(paymentSum)
                        .withPaymentDate(invoicedPaymentDate)
                        .build());
            }
        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }
        DataSource.returnConnection(connection);
        return paymentList;
    }

    public Optional<Payment> getPaymentById(int paymentId){
        Payment payment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payment " +
                "WHERE id = ?")){
            preparedStatement.setObject(1, paymentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                BigDecimal paymentSum = resultSet.getBigDecimal("sum");
                int usersId = resultSet.getInt("users_id");
                Date invoicedPaymentDate = resultSet.getDate("payment_date");

                payment = new Payment.Builder()
                        .withId(paymentId)
                        .withUserId(usersId)
                        .withPaymentSum(paymentSum)
                        .withPaymentDate(invoicedPaymentDate)
                        .build();
            }
            } catch (SQLException e) {
            logger.error("sql error", e);
        }
        DataSource.returnConnection(connection);
        return Optional.ofNullable(payment);
    }

    public void deletePayment(Payment payment){
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM payment " +
                "WHERE id = ?")){
            preparedStatement.setObject(1, payment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("sql error", e);
        }
        DataSource.returnConnection(connection);
    }
}

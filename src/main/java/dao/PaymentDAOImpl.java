package dao;

import dao.connectionpool.ConnectionData;
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

public class PaymentDAOImpl implements PaymentDAO {

    private final static String SELECT_ALL_PAYMENT = "SELECT id, sum, payment_date " +
            "FROM payment  WHERE payment.users_id = ?";
    private final static String SELECT_PAYMENT = "SELECT * FROM payment WHERE id = ?";
    private final static String DELETE_PAYMENT = "DELETE FROM payment WHERE id = ?";

    private final static PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
    public static PaymentDAOImpl getInstance(){
        return paymentDAO;
    }

    private PaymentDAOImpl() {
    }

    private static final Logger logger = Logger.getLogger(PaymentDAOImpl.class);

    public List<Payment> getAllPaymentsForUser(int userId) {
        Connection connection = ConnectionData.getConnection();
        List<Payment> paymentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENT)) {
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        } finally {
            ConnectionData.returnConnection(connection);
        }
        return paymentList;
    }

    public Optional<Payment> getPaymentById(int paymentId) {
        Connection connection = ConnectionData.getConnection();
        Payment payment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT)) {
            preparedStatement.setObject(1, paymentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
        } finally {
            ConnectionData.returnConnection(connection);
        }
        return Optional.ofNullable(payment);
    }

    public void deletePayment(Payment payment) {
        Connection connection = ConnectionData.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT)) {
            preparedStatement.setObject(1, payment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("sql error", e);
        } finally {
            ConnectionData.returnConnection(connection);
        }
    }
}

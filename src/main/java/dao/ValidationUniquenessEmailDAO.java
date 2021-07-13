package dao;

import dao.connectionpool.ConnectionData;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ValidationUniquenessEmailDAO {

    private final static String CHECK_EMAIL = "SELECT email FROM users WHERE email = ?";

    private static final ValidationUniquenessEmailDAO validationUniquenessEmail =
            new ValidationUniquenessEmailDAO();
    public static ValidationUniquenessEmailDAO getInstance(){
        return validationUniquenessEmail;
    }
    private ValidationUniquenessEmailDAO() {
    }

    private Connection connection;
    private static final Logger logger = Logger.getLogger(ValidationUniquenessEmailDAO.class);

    {
        try {
            connection = ConnectionData.getConnection();
        } catch (SQLException | ClassNotFoundException throwable) {
            logger.error("connection error ",throwable);
        }
    }

    public Optional<String> checkEmailForUniqueness(String email){
        String resultQueryEmail = null;
        try (PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL)){
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                resultQueryEmail = resultSet.getObject("email",String.class);
            }
        } catch (SQLException throwable) {
            logger.error("sql exception ",throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return Optional.ofNullable(resultQueryEmail);
    }
}

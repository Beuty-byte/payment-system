package dao;

import dao.connectionpool.DataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ValidationUniquenessEmailDAO {

    private Connection connection;
    private static final Logger logger = Logger.getLogger(ValidationUniquenessEmailDAO.class);

    {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException | ClassNotFoundException throwable) {
            logger.error("connection error ",throwable);
        }
    }

    public void checkEmailForUniqueness(String email, List<String> registerErrors){
        String resultQueryEmail = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT email FROM users WHERE email = ?")){
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                resultQueryEmail = resultSet.getObject("email",String.class);
            }
            if(resultQueryEmail != null){
                registerErrors.add("such email is in the system");
            }
        } catch (SQLException throwable) {
            logger.error("sql exception ",throwable);
        }
    }
}

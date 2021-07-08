package dao;

import dao.connectionpool.DataSource;
import domain.Role;
import domain.SessionObjectForUser;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VerificationOfLoginDetailsDAO {

    private Connection connection;
    private static final Logger logger = Logger.getLogger(VerificationOfLoginDetailsDAO.class);

    {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public Optional<SessionObjectForUser> verificationEmailAndPassword(String email, String password){
        SessionObjectForUser sessionObjectForUser = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, role FROM users " +
                "WHERE email = ? AND password = ?")){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getObject("id",Integer.class) != null){

                    sessionObjectForUser = new SessionObjectForUser(resultSet.getObject("id", Integer.class)
                            , Role.valueOf(resultSet.getObject("role", String.class).toUpperCase()));

                }
            }
        } catch (SQLException throwable) {
            logger.error("sql exception ", throwable);
        }
        DataSource.returnConnection(connection);
        return Optional.ofNullable(sessionObjectForUser);
    }

}

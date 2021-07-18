package dao;

import dao.connectionpool.ConnectionData;
import domain.Role;
import domain.SessionObjectForUser;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VerificationOfLoginDetailsDAOImpl implements VerificationOfLoginDetailsDAO{

    private final static String SELECT_EMAIL_AND_PASSWORD = "SELECT id, role FROM users WHERE email = ? AND password = ?";
    private static final VerificationOfLoginDetailsDAOImpl verificationOfLoginDetailsDAO =
            new VerificationOfLoginDetailsDAOImpl();
    public static VerificationOfLoginDetailsDAOImpl getInstance(){
        return verificationOfLoginDetailsDAO;
    }
    private VerificationOfLoginDetailsDAOImpl() {
    }


    private static final Logger logger = Logger.getLogger(VerificationOfLoginDetailsDAOImpl.class);

    public Optional<SessionObjectForUser> verificationEmailAndPassword(String email, String password){
        Connection connection = ConnectionData.getConnection();
        SessionObjectForUser sessionObjectForUser = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_AND_PASSWORD)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                    sessionObjectForUser = new SessionObjectForUser(resultSet.getObject("id", Integer.class)
                            , Role.valueOf(resultSet.getObject("role", String.class).toUpperCase()));

            }
        } catch (SQLException throwable) {
            logger.error("sql exception ", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return Optional.ofNullable(sessionObjectForUser);
    }

}

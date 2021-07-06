package dao;

import domain.Role;
import domain.SessionObjectForUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificationOfLoginDetailsDAO {

    private Connection connection;

    {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public SessionObjectForUser verificationEmailAndPassword(String email, String password){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, role FROM users " +
                "WHERE email = ? AND password = ?")){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getObject("id",Integer.class) != null){

                    return new SessionObjectForUser(resultSet.getObject("id", Integer.class)
                            , Role.valueOf(resultSet.getObject("role", String.class).toUpperCase()));

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean checkAccessToCreditCardInformation(long creditCardId,int userId){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM credit_card " +
                "WHERE users_id = ? AND id = ?")){
            preparedStatement.setObject(1,userId);
            preparedStatement.setObject(2,creditCardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int amount = resultSet.getInt(1);
            if(amount > 0){
                return true;
            }
            return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

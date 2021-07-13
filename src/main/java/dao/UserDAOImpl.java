package dao;

import dao.connectionpool.ConnectionData;
import domain.CreditCard;
import domain.User;
import org.apache.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDAOImpl implements UserDAO{

    private final static String SELECT_AMOUNT_USERS_IN_SYSTEM = "SELECT COUNT(*) FROM users";
    private final static String SELECT_USER = "SELECT * FROM users WHERE id = ?";
    private final static String REGISTER_USER = "INSERT INTO users (name, surname, password,email) " +
            "VALUES (?, ?, ?, ?)";


    private final static UserDAOImpl userDAO = new UserDAOImpl();
    public static UserDAOImpl getInstance(){
        return userDAO;
    }
    private UserDAOImpl() {
    }

    private static final int SHOW_USERS_ON_PAGE = 5;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public static int getAmountUsersOnPage() {
        return SHOW_USERS_ON_PAGE;
    }

    public int getAmountUsersInSystem(){
        Connection connection = ConnectionData.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_AMOUNT_USERS_IN_SYSTEM);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            ConnectionData.returnConnection(connection);
        }
        throw new IllegalArgumentException();
    }

    public List<User> getAllUser(Integer page, String sortByValue){
        Connection connection = ConnectionData.getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()){

            String sqlQuery = "SELECT * FROM users LIMIT " + SHOW_USERS_ON_PAGE;

            if(page != null && sortByValue != null){
                sqlQuery = "SELECT * FROM users ORDER BY " + sortByValue
                        + " LIMIT " + SHOW_USERS_ON_PAGE + " OFFSET " + (page - 1) * SHOW_USERS_ON_PAGE;
            }else if(page != null){
                sqlQuery = "SELECT * FROM users LIMIT " + SHOW_USERS_ON_PAGE + " OFFSET "
                + (page - 1) * SHOW_USERS_ON_PAGE;
            }else if(sortByValue != null){
                sqlQuery = "SELECT * FROM users ORDER BY "+ sortByValue +" LIMIT " + SHOW_USERS_ON_PAGE;
            }

            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Integer id = resultSet.getObject("id", Integer.class);
                String name = resultSet.getObject("name", String.class);
                String surName = resultSet.getObject("surname", String.class);
                String email = resultSet.getObject("email", String.class);

                User user = new User.Builder().withId(id)
                                .withName(name)
                                .withSurname(surName)
                                .withEmail(email).build();
                users.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return users;
    }

    public Optional<User> getUser(int id){
        Connection connection = ConnectionData.getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER)){
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String name = resultSet.getObject("name", String.class);
            String surname = resultSet.getObject("surname", String.class);
            String email = resultSet.getObject("email", String.class);
            List<CreditCard> creditCardList = CreditCardDAOImpl.getInstance().getAllCreditCardsWithBankAccountForUser(id);
            user = new User.Builder()
                    .withName(name)
                    .withSurname(surname)
                    .withEmail(email)
                    .withCreditCards(creditCardList).build();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return Optional.ofNullable(user);
    }

    public boolean registerUserInSystem(Map<String, String[]> customerData){
        Connection connection = ConnectionData.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(REGISTER_USER)){
            statement.setString(1, customerData.get("name")[0]);
            statement.setString(2, customerData.get("surname")[0]);
            statement.setString(3, customerData.get("password1")[0]);
            statement.setString(4, customerData.get("email")[0]);
            return statement.execute();
        } catch (SQLException throwable) {
            logger.error("sql error ", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return false;
    }
}

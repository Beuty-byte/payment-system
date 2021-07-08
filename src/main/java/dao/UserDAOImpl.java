package dao;

import dao.connectionpool.DataSource;
import domain.CreditCard;
import domain.User;
import org.apache.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO{
    private Connection connection;
    private static final int SHOW_USERS_ON_PAGE = 5;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public static int getShowUsersOnPage() {
        return SHOW_USERS_ON_PAGE;
    }

    public int getAmountUsersInSystem(){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        DataSource.returnConnection(connection);
        throw new IllegalArgumentException();
    }

    public List<User> getAllUser(Integer page, String sortByValue){
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
        }
        DataSource.returnConnection(connection);
        return users;
    }

    public User getUser(int id){
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")){
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String name = resultSet.getObject("name", String.class);
            String surname = resultSet.getObject("surname", String.class);
            String email = resultSet.getObject("email", String.class);
            List<CreditCard> creditCardList = new CreditCardDAOImpl().getAllCreditCardWithBankAccountForUser(id);
            return new User.Builder()
                    .withName(name)
                    .withSurname(surname)
                    .withEmail(email)
                    .withCreditCards(creditCardList).build();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        DataSource.returnConnection(connection);
        throw new RuntimeException();
    }

    public boolean registerUserInSystem(Map<String, String[]> customerData){
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, surname, password,email) " +
                "VALUES (?, ?, ?, ?)")){
            statement.setString(1, customerData.get("name")[0]);
            statement.setString(2, customerData.get("surname")[0]);
            statement.setString(3, customerData.get("password1")[0]);
            statement.setString(4, customerData.get("email")[0]);
            return statement.execute();
        } catch (SQLException throwable) {
            logger.error("sql error ", throwable);
        }
        DataSource.returnConnection(connection);
        return false;
    }
}

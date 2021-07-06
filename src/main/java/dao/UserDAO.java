package dao;

import domain.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    int getAmountUsersInSystem();
    List<User> getAllUser(Integer page, String sortByValue);
    User getUser(int id);
    boolean registerUserInSystem(Map<String, String[]> customerData);
}

package dao;

import domain.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDAO {
    int getAmountUsersInSystem();
    List<User> getAllUser(Integer page, String sortByValue);
    Optional<User> getUser(int id);
    boolean registerUserInSystem(Map<String, String[]> customerData);
}

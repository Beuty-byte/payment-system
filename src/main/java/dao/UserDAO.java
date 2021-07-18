package dao;

import domain.User;

import java.util.List;
import java.util.Optional;

/**
 * interface for work with user DAO
 */
public interface UserDAO {
    /**
     * returns amount users in system
     * @return int
     */
    int getAmountUsersInSystem();

    /**
     * returns users {@link domain.User} with sorting condition and amount users on page
     * @param page show amount users at page
     * @param sortByValue sorting condition
     * @return list users or else return empty list
     */
    List<User> getAllUser(Integer page, String sortByValue);

    /**
     * returns user by id {@link domain.User}
     * @param id user id
     * @return user or else null
     */
    Optional<User> getUser(int id);

    /**
     * register user in system
     * @param name user name
     * @param surname user surname
     * @param password user password
     * @param email user email
     * @return if user create in system return true or else return false
     */
    boolean registerUserInSystem(String name, String surname, String password, String email);
}

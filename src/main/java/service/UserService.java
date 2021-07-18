package service;

import domain.User;

import java.util.List;
import java.util.Optional;

/**
 * interface for work with users
 */
public interface UserService {
    /**
     * returns list with {@link domain.User}
     * @param page page number
     * @param sortByValue sort value
     * @return list with users if they there are, or else return empty list
     */
    List<User> getAllUsers(Optional<String> page, Optional<String> sortByValue);
    /**
     * returns {@link domain.User} by id
     * @param id user id
     * @return return user
     * @throws IllegalArgumentException throws if incorrect parameters were sent
     */
    User getUserById(int id) throws IllegalArgumentException;

    /**
     *  return {@link component.Pagination} for user
     * @param page page number
     * @param sortBy sort value
     * @param Uri url
     * @return pagination for UI
     */
    String getPaginationForUsers(Optional<String> page, Optional<String> sortBy, String Uri);
}

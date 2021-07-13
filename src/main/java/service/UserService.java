package service;

import domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers(Optional<String> page, Optional<String> sortByValue);
    User getUserById(int id);
    String getPaginationForUsers(Optional<String> page, Optional<String> sortBy, String Uri);
}

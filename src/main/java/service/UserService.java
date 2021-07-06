package service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<domain.User> getAllUsers(Optional<String> page, Optional<String> sortByValue);
    domain.User getUserById(int id);
}

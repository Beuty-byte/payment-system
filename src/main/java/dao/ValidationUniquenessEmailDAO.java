package dao;

import java.util.Optional;

/**
 * interface for validation uniqueness email DAO
 */
public interface ValidationUniquenessEmailDAO {
    /**
     * check email for check email for uniqueness
     * @param email input email
     * @return if email there is in system return this email or else return null
     */
    Optional<String> checkEmailForUniqueness(String email);
}

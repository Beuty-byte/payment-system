package service;

import domain.SessionObjectForUser;

/**
 * interface for checked login details
 */
public interface CheckedLoginDetails {
    /**
     * returns {@link domain.SessionObjectForUser} for user with him id and role
     * @param email user email
     * @param password user password
     * @return session object for user
     * @throws IllegalArgumentException throws if incorrect parameters were sent
     */
    SessionObjectForUser loginDataValidation(String email, String password) throws IllegalArgumentException;
}

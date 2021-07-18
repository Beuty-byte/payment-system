package dao;

import domain.SessionObjectForUser;

import java.util.Optional;

/**
 * interface for verification login details
 */
public interface VerificationOfLoginDetailsDAO {
    /**
     * returns {@link domain.SessionObjectForUser} if user there is in system
     * @param email user email
     * @param password  user password
     * @return  session object for user or else null
     */
    Optional<SessionObjectForUser> verificationEmailAndPassword(String email, String password);
}

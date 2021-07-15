package dao;

import domain.SessionObjectForUser;

import java.util.Optional;

public interface VerificationOfLoginDetailsDAO {
    Optional<SessionObjectForUser> verificationEmailAndPassword(String email, String password);
}

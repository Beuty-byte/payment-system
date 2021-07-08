package service;

import domain.SessionObjectForUser;

import java.util.Map;

public interface CheckedLoginDetails {
    SessionObjectForUser loginDataValidation(String email, String password);
}

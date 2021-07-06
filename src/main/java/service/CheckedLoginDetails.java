package service;

import domain.SessionObjectForUser;

import java.util.Map;

public interface CheckedLoginDetails {
    SessionObjectForUser dataChecking(Map<String,String[]> dataWithForm);
}

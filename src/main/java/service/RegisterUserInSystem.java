package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import util.PasswordEncoder;
import util.PasswordEncoderImpl;

import java.util.Map;

public class RegisterUserInSystem implements Registered{

    private static final RegisterUserInSystem INSTANCE = new RegisterUserInSystem();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final PasswordEncoder passwordEncoder = PasswordEncoderImpl.getInstance();

    private RegisterUserInSystem(){}

    public static Registered getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean registerInSystem(Map<String, String[]> parameterMap) {
        String userName = parameterMap.get("name")[0];
        String userSurname = parameterMap.get("surname")[0];
        String encodedPassword = passwordEncoder.getEncoderPassword(parameterMap.get("password1")[0]);
        String email = parameterMap.get("email")[0];
        return  userDAO.registerUserInSystem(userName, userSurname, encodedPassword, email);
    }
}

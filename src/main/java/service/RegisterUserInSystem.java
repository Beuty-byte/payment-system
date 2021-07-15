package service;

import dao.UserDAO;
import dao.UserDAOImpl;

import java.util.Map;

public class RegisterUserInSystem implements Registered{

    private static final RegisterUserInSystem INSTANCE = new RegisterUserInSystem();
    private final UserDAO userDAO = UserDAOImpl.getInstance();

    private RegisterUserInSystem(){}

    public static Registered getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean registerInSystem(Map<String, String[]> parameterMap) {
        return  userDAO.registerUserInSystem(parameterMap);
    }
}

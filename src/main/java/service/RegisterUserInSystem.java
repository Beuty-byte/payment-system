package service;

import dao.UserDAOImpl;

import java.util.Map;

public class RegisterUserInSystem implements Registered{

    private static final RegisterUserInSystem registerUserInSystem = new RegisterUserInSystem();

    private RegisterUserInSystem(){}

    public static Registered getInstance(){
        return registerUserInSystem;
    }

    @Override
    public boolean registerInSystem(Map<String, String[]> parameterMap) {
        return new UserDAOImpl().registerUserInSystem(parameterMap);
    }
}

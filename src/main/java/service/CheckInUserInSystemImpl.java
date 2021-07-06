package service;

import dao.UserDAOImpl;

import java.util.Map;

public class CheckInUserInSystemImpl implements Registered{

    private static final Registered checkIn = new CheckInUserInSystemImpl();

    private CheckInUserInSystemImpl(){}

    public static Registered getInstance(){
        return checkIn;
    }

    @Override
    public boolean registerInSystem(Map<String, String[]> parameterMap) {
        return new UserDAOImpl().registerUserInSystem(parameterMap);
    }
}

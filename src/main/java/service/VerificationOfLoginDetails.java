package service;


import dao.VerificationOfLoginDetailsDAO;
import domain.SessionObjectForUser;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Optional;

public class VerificationOfLoginDetails implements CheckedLoginDetails{

    private static final VerificationOfLoginDetails verificationOfLoginDetails = new VerificationOfLoginDetails();
    public static VerificationOfLoginDetails getInstance(){
        return verificationOfLoginDetails;
    }

    private VerificationOfLoginDetails(){}

    @Override
    public SessionObjectForUser loginDataValidation(String email, String password){
        return VerificationOfLoginDetailsDAO.getInstance().verificationEmailAndPassword(email, password)
        .orElseThrow(IllegalArgumentException::new);
    }
}

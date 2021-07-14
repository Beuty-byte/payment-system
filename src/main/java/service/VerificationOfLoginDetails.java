package service;


import dao.VerificationOfLoginDetailsDAO;
import domain.SessionObjectForUser;

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

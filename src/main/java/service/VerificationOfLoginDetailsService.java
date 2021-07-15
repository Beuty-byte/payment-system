package service;


import dao.VerificationOfLoginDetailsDAO;
import dao.VerificationOfLoginDetailsDAOImpl;
import domain.SessionObjectForUser;

public class VerificationOfLoginDetailsService implements CheckedLoginDetails{

    private static final VerificationOfLoginDetailsService INSTANCE = new VerificationOfLoginDetailsService();
    public static VerificationOfLoginDetailsService getInstance(){
        return INSTANCE;
    }
    private final VerificationOfLoginDetailsDAO verification = VerificationOfLoginDetailsDAOImpl.getInstance();

    private VerificationOfLoginDetailsService(){}

    @Override
    public SessionObjectForUser loginDataValidation(String email, String password){
        return verification.verificationEmailAndPassword(email, password)
        .orElseThrow(IllegalArgumentException::new);
    }
}

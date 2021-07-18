package service;


import dao.VerificationOfLoginDetailsDAO;
import dao.VerificationOfLoginDetailsDAOImpl;
import domain.SessionObjectForUser;
import util.PasswordEncoder;
import util.PasswordEncoderImpl;

public class VerificationOfLoginDetailsService implements CheckedLoginDetails{

    private static final VerificationOfLoginDetailsService INSTANCE = new VerificationOfLoginDetailsService();
    public static VerificationOfLoginDetailsService getInstance(){
        return INSTANCE;
    }
    private final VerificationOfLoginDetailsDAO verification = VerificationOfLoginDetailsDAOImpl.getInstance();
    private final PasswordEncoder passwordEncoder = PasswordEncoderImpl.getInstance();

    private VerificationOfLoginDetailsService(){}

    @Override
    public SessionObjectForUser loginDataValidation(String email, String password){
        return verification.verificationEmailAndPassword(email, passwordEncoder.getEncoderPassword(password))
        .orElseThrow(IllegalArgumentException::new);
    }
}

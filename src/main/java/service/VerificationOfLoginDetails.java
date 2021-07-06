package service;


import dao.VerificationOfLoginDetailsDAO;
import domain.SessionObjectForUser;

import java.util.Map;

public class VerificationOfLoginDetails implements CheckedLoginDetails{

    private static final VerificationOfLoginDetails verificationOfLoginDetails = new VerificationOfLoginDetails();

    public static VerificationOfLoginDetails getInstance(){
        return verificationOfLoginDetails;
    }

    private VerificationOfLoginDetails(){}


    @Override
    public SessionObjectForUser dataChecking(Map<String,String[]> dataWithForm){
        String password = null;
        String email = null;
        for(Map.Entry<String, String[]> el : dataWithForm.entrySet()){
            if(el.getKey().equals("email")){
                email = el.getValue()[0];
            }
            if(el.getKey().equals("password")){
                password = el.getValue()[0];
            }
        }
        return new VerificationOfLoginDetailsDAO().verificationEmailAndPassword(email, password);
    }
}

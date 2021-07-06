package service;


import dao.ValidationUniquenessEmailDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidationRegisterForm implements VerifiableData {

    private static final ValidationRegisterForm validationRegisterForm = new ValidationRegisterForm();

    public static ValidationRegisterForm getInstance(){
        return validationRegisterForm;
    }

    private ValidationRegisterForm(){}

    @Override
    public List<String> validate(Map<String, String[]> dataWithForm){
         List<String> registerErrors = new ArrayList<>();
         List<String> passwords = new ArrayList<>();
        ValidationUniquenessEmailDAO validationUniquenessEmailDAO = new ValidationUniquenessEmailDAO();

        for (Map.Entry<String,String[]> el : dataWithForm.entrySet()){
            if(el.getKey().equals("name")){
                checkNameValueFromForm(el.getValue()[0], registerErrors);
            }else if(el.getKey().equals("surname")){
                checkSurnameValueFromForm(el.getValue()[0], registerErrors);
            }else if(el.getKey().equals("password1") || el.getKey().equals("password2")){
                passwords.add(el.getValue()[0]);
            }else if(el.getKey().equals("email")){
                checkEmailFromFormOnValid(el.getValue()[0], registerErrors);
                validationUniquenessEmailDAO.checkEmailForUniqueness(el.getValue()[0] ,registerErrors);
            }
        }
        checkPasswordFromForm(passwords.get(0),passwords.get(1), registerErrors);
        return registerErrors;
    }


    private void checkEmailFromFormOnValid(String email, List<String> registerErrors){
        if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            registerErrors.add("impossible values for email");
        }
    }

    private void checkNameValueFromForm(String name, List<String> registerErrors){
            if(name.length() < 2 || name.length() > 14){
                registerErrors.add("Name should be more than 1 symbol or less than 15");
            }
    }

    private void checkSurnameValueFromForm(String surname, List<String> registerErrors){
        if(surname.length() < 2 || surname.length() > 14){
            registerErrors.add("Surname should be more than 1 symbol or less than 15");
        }
    }

    private void checkPasswordFromForm(String password1, String password2, List<String> registerErrors){
        if(!password1.equals(password2)){
            registerErrors.add("Password should be equals");
        }
        if(password1.length() < 8){
            registerErrors.add("Password should be more than 8 symbol");
        }
        if(!password1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
            registerErrors.add("must be one uppercase letter, one lowercase letter and one number");
        }
    }

}

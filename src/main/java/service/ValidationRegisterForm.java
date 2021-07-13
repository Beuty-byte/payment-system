package service;


import dao.ValidationUniquenessEmailDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ValidationRegisterForm implements VerifiableData {

    private static final ValidationRegisterForm validationRegisterForm = new ValidationRegisterForm();

    public static ValidationRegisterForm getInstance(){
        return validationRegisterForm;
    }

    private ValidationRegisterForm(){}

    @Override
    public List<String> validate(Map<String, String[]> dataWithForm, ResourceBundle lang){
         List<String> registerErrors = new ArrayList<>();

        String name = dataWithForm.get("name")[0];
        String surname = dataWithForm.get("surname")[0];
        String password1 = dataWithForm.get("password1")[0];
        String password2 = dataWithForm.get("password2")[0];
        String email = dataWithForm.get("email")[0];

        checkNameValueFromForm(name, registerErrors, lang);
        checkSurnameValueFromForm(surname, registerErrors, lang);
        checkPasswordFromForm(password1, password2, registerErrors, lang);
        checkEmailFromFormOnValid(email, registerErrors, lang);

        return registerErrors;
    }


    private void checkEmailFromFormOnValid(String email, List<String> registerErrors, ResourceBundle lang){
        if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            registerErrors.add(lang.getString("validationRegisterFormCheckEmailFromFormOnValid"));
        }
        if(ValidationUniquenessEmailDAO.getInstance().checkEmailForUniqueness(email).isPresent()){
            registerErrors.add(lang.getString("validationRegisterFormCheckEmailForUniqueness"));
        }
    }

    private void checkNameValueFromForm(String name, List<String> registerErrors, ResourceBundle lang){
            if(name.length() < 2 || name.length() > 14){
                registerErrors.add(lang.getString("validationRegisterFormCheckNameValue"));
            }
    }

    private void checkSurnameValueFromForm(String surname, List<String> registerErrors, ResourceBundle lang){
        if(surname.length() < 2 || surname.length() > 14){
            registerErrors.add(lang.getString("validationRegisterFormCheckSurnameValue"));
        }
    }

    private void checkPasswordFromForm(String password1, String password2, List<String> registerErrors, ResourceBundle lang){
        if(!password1.equals(password2)){
            registerErrors.add(lang.getString("validationRegisterFormCheckPasswordOnEquals"));
        }
        if(password1.length() < 8){
            registerErrors.add(lang.getString("validationRegisterFormCheckPasswordOnLength"));
        }
        if(!password1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")){
            registerErrors.add(lang.getString("validationRegisterFormCheckPasswordOnUppercaseAndNumber"));
        }
    }

}

package service;


import dao.CreditCardDAO;
import dao.CreditCardDAOImpl;
import domain.CreditCard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CheckedDataWithFormImpl implements CheckDataWithFormService {

    private static final CheckedDataWithFormImpl INSTANCE = new CheckedDataWithFormImpl();
    private final CreditCardDAO creditCardDAO = CreditCardDAOImpl.getInstance();

    public static CheckedDataWithFormImpl getInstance(){
        return INSTANCE;
    }

    private CheckedDataWithFormImpl() {
    }

    @Override
    public List<String> checkData(Map<String, String[]> dataWithForm, long creditCardId, ResourceBundle lang) {
        List<String> validationsErrors = new ArrayList<>();

        if(dataWithForm.containsKey("amount")) {
            validateInputValue(validationsErrors, dataWithForm.get("amount")[0], lang);
        }
        cardIsBlocked(validationsErrors, creditCardId, lang);

        return validationsErrors;
    }

    private void cardIsBlocked(List<String> validationsErrors, long creditCardId, ResourceBundle lang){
        CreditCard creditCardById = creditCardDAO.getCreditCardById(creditCardId)
                .orElseThrow(NumberFormatException::new);
        if(creditCardById.getBankAccount().getBlocked()){
            validationsErrors.add(lang.getString("checkDataWithFormCardIsBlocked"));
        }
    }

    private void validateInputValue(List<String> validationErrors, String inputAmountMoney, ResourceBundle lang){
        try {
            BigDecimal amountMoney = BigDecimal.valueOf(Double.parseDouble(inputAmountMoney));
        }catch (NumberFormatException e){
            validationErrors.add(lang.getString("checkDataWithFormValidateInputValue"));
        }
    }
}

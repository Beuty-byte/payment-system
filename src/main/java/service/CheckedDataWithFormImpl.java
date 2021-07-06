package service;


import dao.CreditCardDAOImpl;
import domain.CreditCard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckedDataWithFormImpl implements CheckDataWithFormService {

    private static final CheckedDataWithFormImpl checkedDataWithForm = new CheckedDataWithFormImpl();

    public static CheckedDataWithFormImpl getInstance(){
        return checkedDataWithForm;
    }

    private CheckedDataWithFormImpl() {
    }

    @Override
    public List<String> checkData(Map<String, String[]> dataWithForm, long creditCardId) {
        List<String> validationsErrors = new ArrayList<>();
        for(Map.Entry<String,String[]> el: dataWithForm.entrySet()){
            if(el.getKey().equals("amount")){
                validateInputValue(validationsErrors, el.getValue()[0]);
                cardIsBlocked(validationsErrors, creditCardId);
            }
        }
        return validationsErrors;
    }

    private void cardIsBlocked(List<String> validationsErrors, long creditCardId){
        CreditCard creditCardById = new CreditCardDAOImpl().getCreditCardById(creditCardId);
        if(creditCardById.getBankAccount().getBlocked()){
            validationsErrors.add("you cannot top up your balance, your card is blocked");
        }
    }

    private void validateInputValue(List<String> validationErrors, String inputAmountMoney){
        try {
            BigDecimal amountMoney = BigDecimal.valueOf(Double.parseDouble(inputAmountMoney));
        }catch (NumberFormatException e){
            validationErrors.add("wrong format number");
        }
    }
}

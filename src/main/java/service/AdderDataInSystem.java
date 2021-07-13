package service;

import dao.CreditCardDAOImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class AdderDataInSystem {

    private static final AdderDataInSystem adderDataInSystem= new AdderDataInSystem();

    public static AdderDataInSystem getInstance(){
        return adderDataInSystem;
    }

    private AdderDataInSystem() {
    }

    public void putData(Map<String,String[]> dataWithForm, long creditCardId) {

            if(dataWithForm.containsKey("amount")){
                putDataInDb(dataWithForm.get("amount")[0], creditCardId);
            }
            if(dataWithForm.containsKey("block")){
                getBlockCreditCard(creditCardId);
            }
            if(dataWithForm.containsKey("unBlock")){
                int bankAccountId = Integer.parseInt(dataWithForm.get("unBlock")[0]);
                unblockCreditCardByBankAccountId(bankAccountId);
            }
    }

    private void getBlockCreditCard(long idCreditCard){
        CreditCardDAOImpl.getInstance()
                .setBlockOnBankAccount(idCreditCard);
    }

    private void unblockCreditCardByBankAccountId(int bankAccountId){
        CreditCardDAOImpl.getInstance().unsetBlockOnBankAccount(bankAccountId);
    }

    private void putDataInDb(String value, long creditCardId){
        CreditCardDAOImpl.getInstance()
                .updateData((BigDecimal.valueOf(Double.parseDouble(value))
                        .setScale(2, RoundingMode.HALF_DOWN))
                        ,creditCardId);
    }
}

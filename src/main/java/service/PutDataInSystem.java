package service;

import dao.CreditCardDAOImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PutDataInSystem{

    private static final PutDataInSystem putDataInSystem= new PutDataInSystem();

    public static PutDataInSystem getInstance(){
        return putDataInSystem;
    }

    private PutDataInSystem() {
    }

    public void putData(Map<String,String[]> dataWithForm, long creditCardId) {
        for(Map.Entry<String,String[]> el : dataWithForm.entrySet()){
            if(el.getKey().equals("amount")){
                putDataInDb(el.getValue()[0], creditCardId);
            }
            if(el.getKey().equals("block")){
                getBlockCreditCard(creditCardId);
            }
            if(el.getKey().equals("unBlock")){
                int bankAccountId = Integer.parseInt(el.getValue()[0]);
                unblockCreditCardByBankAccountId(bankAccountId);
            }
        }
    }

    private void getBlockCreditCard(long idCreditCard){
        new CreditCardDAOImpl().setBlockOnBankAccount(idCreditCard);
    }

    private void unblockCreditCardByBankAccountId(int bankAccountId){
        new CreditCardDAOImpl().unsetBlockOnBankAccount(bankAccountId);
    }

    private void putDataInDb(String value, long creditCardId){
        new CreditCardDAOImpl()
                .updateData((BigDecimal.valueOf(Double.parseDouble(value))
                        .setScale(2, RoundingMode.HALF_DOWN))
                        ,creditCardId);
    }
}

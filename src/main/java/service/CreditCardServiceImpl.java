package service;


import dao.CreditCardDAO;
import dao.CreditCardDAOImpl;
import domain.CreditCard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardDAO creditCardDAO = CreditCardDAOImpl.getInstance();

    private static final CreditCardServiceImpl INSTANCE = new CreditCardServiceImpl();

    public static CreditCardServiceImpl getInstance(){
        return INSTANCE;
    }

    private CreditCardServiceImpl(){
    }

    @Override
    public List<CreditCard> getAllCreditsCardForUser(int userId) {
        return creditCardDAO.getAllCreditCardsWithBankAccountForUser(userId);
    }

    @Override
    public CreditCard getCreditCardById(long creditCardId, int userId){
        boolean access = userAccessToCreditCard(creditCardId, userId);
        if(access){
            return creditCardDAO
                    .getCreditCardById(creditCardId)
                    .orElseThrow(NumberFormatException::new);
        }
        return null;
    }

    @Override
    public boolean userAccessToCreditCard(long creditCardId, int userId){
        return creditCardDAO.checkAccessToCreditCardInformation(creditCardId, userId);
    }

    @Override
    public String getIdForPrettyPrint(long id){
        String idForPrint = String.valueOf(id);
        return transformId(idForPrint.toCharArray());
    }

    @Override
    public String getTotalBalance(int userId) {
        BigDecimal totalBalance = CreditCardDAOImpl.getInstance()
                .getTotalBalance(userId)
                .orElseThrow(IllegalArgumentException::new);
        return totalBalance.toString();
    }

    private String transformId(char[] idForPrint){
        StringBuilder stringBuilder = new StringBuilder();
        int countBeforeDrop = 0;
        for (char el : idForPrint){
            if(countBeforeDrop == 4){
                stringBuilder.append(' ').append(el);
                countBeforeDrop = 0;
            }else {
                stringBuilder.append(el);
            }
            countBeforeDrop++;
        }
        return stringBuilder.toString();
    }

    @Override
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
        creditCardDAO.setBlockOnBankAccount(idCreditCard);
    }

    private void unblockCreditCardByBankAccountId(int bankAccountId){
        creditCardDAO.unsetBlockOnBankAccount(bankAccountId);
    }

    private void putDataInDb(String value, long creditCardId){
        creditCardDAO.updateData((BigDecimal.valueOf(Double.parseDouble(value))
                        .setScale(2, RoundingMode.HALF_DOWN))
                        ,creditCardId);
    }

}

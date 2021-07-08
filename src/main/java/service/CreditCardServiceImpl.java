package service;

import dao.CreditCardDAOImpl;
import domain.CreditCard;

import java.math.BigDecimal;
import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

    private static final CreditCardServiceImpl creditCardService = new CreditCardServiceImpl();


    public static CreditCardServiceImpl getInstance(){
        return creditCardService;
    }

    private CreditCardServiceImpl(){
    }

    @Override
    public List<CreditCard> getAllCreditsCardForUser(int userId) {
        return new CreditCardDAOImpl().getAllCreditCardWithBankAccountForUser(userId);
    }

    @Override
    public CreditCard getCreditCardById(long creditCardId, int userId){
        boolean access = userAccessToCreditCard(creditCardId, userId);
        if(access){
            return new CreditCardDAOImpl()
                    .getCreditCardById(creditCardId)
                    .orElseThrow(NumberFormatException::new);
        }
        return null;
    }


    private boolean userAccessToCreditCard(long creditCardId, int userId){
        return new CreditCardDAOImpl().checkAccessToCreditCardInformation(creditCardId, userId);
    }

    @Override
    public String getIdForPrettyPrint(long id){
        String idForPrint = String.valueOf(id);
        return transformId(idForPrint.toCharArray());
    }

    @Override
    public String getTotalBalance(int userId) {
        BigDecimal totalBalance = new CreditCardDAOImpl()
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
}

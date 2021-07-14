package service;



import domain.CreditCard;

import java.util.List;
import java.util.Map;

public interface CreditCardService {
    List<CreditCard> getAllCreditsCardForUser(int userId);

    CreditCard getCreditCardById(long creditCardId, int userId);

    String getIdForPrettyPrint(long id);

    String getTotalBalance(int userId);

    boolean userAccessToCreditCard(long creditCardId, int userId);
    void putData(Map<String,String[]> dataWithForm, long creditCardId);
}

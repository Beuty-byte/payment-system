package service;



import domain.CreditCard;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> getAllCreditsCardForUser(int userId);

    CreditCard getCreditCardById(long creditCardId, int userId);

    String getIdForPrettyPrint(long id);

    String getTotalBalance(int userId);

    boolean userAccessToCreditCard(long creditCardId, int userId);
}

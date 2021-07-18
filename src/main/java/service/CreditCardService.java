package service;



import domain.CreditCard;

import java.util.List;
import java.util.Map;

/**
 * interface for work with credit card
 */
public interface CreditCardService {
    /**
     * returns list {@link domain.CreditCard} for user
     * @param userId user id
     * @return list credit card if they there are or else return empty list
     */
    List<CreditCard> getAllCreditsCardForUser(int userId);
    /**
     *  returns {@link domain.CreditCard} by credit card id and user id
     * @param creditCardId credit card id
     * @param userId user id
     * @return credit card
     * @throws NumberFormatException throws if incorrect parameters were sent
     */
    CreditCard getCreditCardById(long creditCardId, int userId) throws NumberFormatException;

    /**
     * returns total balance all credits card for user
     * @param userId user id
     * @return total balance for user
     * @throws IllegalArgumentException throws if incorrect parameters were sent
     */
    String getTotalBalance(int userId) throws IllegalArgumentException;

    /**
     * check user access to credit card
     * @param creditCardId credit card id
     * @param userId user id
     * @return true if user have access to credit card or else return false
     */
    boolean userAccessToCreditCard(long creditCardId, int userId);

    /**
     *  put data in system
     * @param dataWithForm data with form
     * @param creditCardId  credit card id
     */
    void putData(Map<String,String[]> dataWithForm, long creditCardId);
}

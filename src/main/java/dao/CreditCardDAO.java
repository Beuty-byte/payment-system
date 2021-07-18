package dao;

import domain.CreditCard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * interface for work with credit card DAO
 */
public interface CreditCardDAO {
    /**
     * returns list credits card {@link domain.CreditCard} for user with bank account
     * @param id it's user id
     * @return list credits card if they there are, or return empty list
     */
    List<CreditCard> getAllCreditCardsWithBankAccountForUser(int id);

    /**
     * returns total balance for user with all him credit cards
     * @param id it's user id
     * @return BigDecimal or if for this user don't have credit cards return null
     */
    Optional<BigDecimal> getTotalBalance(int id);

    /**
     * return {@link domain.CreditCard} for user
     * @param id it's user id
     * @return credit card or null
     */
    Optional<CreditCard> getCreditCardById(long id);

    /**
     * update credit card balance
     * @param bigDecimal sum for update
     * @param creditCardId  credit card id for update
     */
    void updateData(BigDecimal bigDecimal, long creditCardId);

    /**
     *  set block on a bank account
     * @param creditCardId credit card id
     */
    void setBlockOnBankAccount(long creditCardId);

    /**
     * unset block on bank account
     * @param bankAccountId bank account id
     */
    void unsetBlockOnBankAccount(int bankAccountId);

    /**
     * check access to credit card information for current user
     * @param creditCardId credit card id
     * @param userId  user id
     * @return  if user have access to credit card return true or else return false
     */
    boolean checkAccessToCreditCardInformation(long creditCardId,int userId);
}

package dao;

import domain.BankAccount;
import domain.CreditCard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CreditCardDAO {
    List<CreditCard> getAllCreditCardWithBankAccountForUser(int id);
    Optional<BigDecimal> getTotalBalance(int id);
    Optional<CreditCard> getCreditCardById(long id);
    void updateData(BigDecimal bigDecimal, long creditCardId);
    void setBlockOnBankAccount(long creditCardId);
    void unsetBlockOnBankAccount(int bankAccountId);
}

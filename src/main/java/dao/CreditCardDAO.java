package dao;

import domain.BankAccount;
import domain.CreditCard;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardDAO {
    List<CreditCard> getAllCreditCardWithBankAccountForUser(int id);
    BigDecimal getTotalBalance(int id);
    CreditCard getCreditCardById(long id);
    void updateData(BigDecimal bigDecimal, long creditCardId);
    void setBlockOnBankAccount(long creditCardId);
    void unsetBlockOnBankAccount(int bankAccountId);
}

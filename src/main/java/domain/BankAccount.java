package domain;

import java.math.BigDecimal;

public class BankAccount {
    private int id;
    private BigDecimal balance;
    private Boolean isBlocked;

    public BankAccount(int id, BigDecimal balance, Boolean isBlocked) {
        this.id = id;
        this.balance = balance;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}

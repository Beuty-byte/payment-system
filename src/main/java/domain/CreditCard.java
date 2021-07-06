package domain;

public class CreditCard {
    private long id;
    private String idForUserView;
    private BankAccount bankAccount;
    private String cardName;

    public CreditCard(long id, BankAccount bankAccount, String cardName, String idForUserView) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.cardName = cardName;
        this.idForUserView = idForUserView;
    }

    public String getIdForUserView() {
        return idForUserView;
    }

    public void setIdForUserView(String idForUserView) {
        this.idForUserView = idForUserView;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}

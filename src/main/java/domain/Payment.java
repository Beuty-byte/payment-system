package domain;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private int id;
    private long creditCardId;
    private BigDecimal sum;
    private Date invoicedPaymentDate;

    public Payment(int id, long creditCardId, BigDecimal sum, Date invoicedPaymentDate) {
        this.id = id;
        this.creditCardId = creditCardId;
        this.sum = sum;
        this.invoicedPaymentDate = invoicedPaymentDate;
    }

    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getInvoicedPaymentDate() {
        return invoicedPaymentDate;
    }

    public void setInvoicedPaymentDate(Date invoicedPaymentDate) {
        this.invoicedPaymentDate = invoicedPaymentDate;
    }
}

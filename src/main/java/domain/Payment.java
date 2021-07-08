package domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Payment {
    private int id;
    private int userId;
    private BigDecimal sum;
    private Date invoicedPaymentDate;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public Date getInvoicedPaymentDate() {
        return invoicedPaymentDate;
    }

    public static class Builder{
        private Payment newPayment;

        public Builder(){
           newPayment  = new Payment();
        }

        public Payment.Builder withId(int id){
            newPayment.id = id;
            return this;
        }

        public Payment.Builder withUserId(int userId){
            newPayment.userId = userId;
            return this;
        }

        public Payment.Builder withPaymentSum(BigDecimal paymentSum){
            newPayment.sum = paymentSum;
            return this;
        }

        public Payment.Builder withPaymentDate(Date paymentDate){
            newPayment.invoicedPaymentDate = paymentDate;
            return this;
        }

        public Payment build(){
            return newPayment;
        }
    }
}

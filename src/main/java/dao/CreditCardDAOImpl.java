package dao;

import domain.BankAccount;
import domain.CreditCard;
import service.CreditCardServiceImpl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDAOImpl implements CreditCardDAO{
    private Connection connection;
    private final CreditCardServiceImpl creditCardInfo = CreditCardServiceImpl.getInstance();
    {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<CreditCard> getAllCreditCardWithBankAccountForUser(int id){
        List<CreditCard> creditCardList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM credit_card WHERE users_id = ?")){
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long creditCardId = resultSet.getObject("id",Long.class);
                int bankAccountId = resultSet.getObject("bank_account_id", Integer.class);
                String creditCardName = resultSet.getObject("name",String.class);

                creditCardList.add(new CreditCard(creditCardId,getBankAccount(bankAccountId)
                        , creditCardName,creditCardInfo.getIdForPrettyPrint(creditCardId)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return creditCardList;
    }

    public BigDecimal getTotalBalance(int id){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(ba.balance) FROM bank_account ba" +
                " JOIN credit_card cr ON cr.bank_account_id = ba.id WHERE cr.users_id = ?")){
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return  resultSet.getBigDecimal(1).setScale(2, RoundingMode.HALF_DOWN);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public CreditCard getCreditCardById(long id){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM credit_card WHERE id = ?")){
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                long creditCardId = resultSet.getObject("id",Long.class);
                int bankAccountId = resultSet.getObject("bank_account_id", Integer.class);
                String creditCardName = resultSet.getObject("name",String.class);

                return new CreditCard(creditCardId, getBankAccount(bankAccountId)
                        ,creditCardName, creditCardInfo.getIdForPrettyPrint(id));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private BankAccount getBankAccount(int id){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank_account WHERE id = ?")){
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                return new BankAccount(
                        resultSet.getObject("id",Integer.class)
                        ,resultSet.getBigDecimal("balance")
                        ,resultSet.getBoolean("is_blocked")
                );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void updateData(BigDecimal bigDecimal, long creditCardId) {
        CreditCard creditCard = new CreditCardDAOImpl().getCreditCardById(creditCardId);
        BigDecimal sumForUpdate = bigDecimal.add(creditCard.getBankAccount().getBalance())
                .setScale(2, RoundingMode.HALF_DOWN);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bank_account SET balance = " +
                "? WHERE ID = ?")){
            preparedStatement.setObject(1,sumForUpdate);
            preparedStatement.setObject(2,creditCard.getBankAccount().getId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void setBlockOnBankAccount(long creditCardId) {
        CreditCard creditCard = new CreditCardDAOImpl().getCreditCardById(creditCardId);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bank_account SET " +
                "is_blocked = true WHERE id = ?")){
            preparedStatement.setObject(1, creditCard.getBankAccount().getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void unsetBlockOnBankAccount(int bankAccountId){
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bank_account SET " +
                "is_blocked = false WHERE id = ?")){
            preparedStatement.setObject(1, bankAccountId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
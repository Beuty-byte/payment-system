package dao;

import dao.connectionpool.ConnectionData;
import domain.BankAccount;
import domain.CreditCard;
import org.apache.log4j.Logger;
import service.CreditCardServiceImpl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditCardDAOImpl implements CreditCardDAO{

    private final static String SELECT_CREDIT_CARDS = "SELECT * FROM credit_card WHERE users_id = ?";
    private final static String SELECT_TOTAL_BALANCE = "SELECT SUM(ba.balance) FROM bank_account ba" +
            " JOIN credit_card cr ON cr.bank_account_id = ba.id WHERE cr.users_id = ?";
    private final static String SELECT_CREDIT_CARD = "SELECT * FROM credit_card WHERE id = ?";
    private final static String SELECT_BANK_ACCOUNT = "SELECT * FROM bank_account WHERE id = ?";
    private final static String UPDATE_DATA_BANK_ACCOUNT = "UPDATE bank_account SET balance =" +
            " ? WHERE ID = ?";
    private final static String SET_BLOCK = "UPDATE bank_account SET " +
            "is_blocked = true WHERE id = ?";
    private final static String UNSET_BLOCK = "UPDATE bank_account SET " +
            "is_blocked = false WHERE id = ?";
    private final static String CHECK_ACCESS_TO_CREDIT_CARD = "SELECT COUNT(*) FROM credit_card " +
            "WHERE users_id = ? AND id = ?";

    final static Logger logger = Logger.getLogger(CreditCardDAOImpl.class);

    private final static CreditCardDAOImpl creditCardDAO = new CreditCardDAOImpl();
    public static CreditCardDAOImpl getInstance(){
        return creditCardDAO;
    }
    private CreditCardDAOImpl() {
    }


    public List<CreditCard> getAllCreditCardsWithBankAccountForUser(int id){
        Connection connection = ConnectionData.getConnection();
        List<CreditCard> creditCardList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CREDIT_CARDS)){
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long creditCardId = resultSet.getObject("id",Long.class);
                int bankAccountId = resultSet.getObject("bank_account_id", Integer.class);
                String creditCardName = resultSet.getObject("name",String.class);
                creditCardList.add(new CreditCard(creditCardId,getBankAccount(bankAccountId)
                        , creditCardName, CreditCardServiceImpl.getInstance().getIdForPrettyPrint(creditCardId)));
            }
        } catch (SQLException throwable) {
            logger.error("wrong format id", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return creditCardList;
    }

    public Optional<BigDecimal> getTotalBalance(int id){
        Connection connection = ConnectionData.getConnection();
        BigDecimal totalBalance = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL_BALANCE)){
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                totalBalance = resultSet.getBigDecimal(1).setScale(2, RoundingMode.HALF_DOWN);
            }
        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return Optional.ofNullable(totalBalance);
    }

    public Optional<CreditCard> getCreditCardById(long id){
        Connection connection = ConnectionData.getConnection();
        CreditCard creditCard = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CREDIT_CARD)){
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                long creditCardId = resultSet.getObject("id", Long.class);
                int bankAccountId = resultSet.getObject("bank_account_id", Integer.class);
                String creditCardName = resultSet.getObject("name", String.class);

                creditCard = new CreditCard(creditCardId, getBankAccount(bankAccountId)
                        , creditCardName, CreditCardServiceImpl.getInstance().getIdForPrettyPrint(id));
            }

        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return Optional.ofNullable(creditCard);
    }

    private BankAccount getBankAccount(int id){
        Connection connection = ConnectionData.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BANK_ACCOUNT)){
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                return new BankAccount(
                        resultSet.getObject("id",Integer.class)
                        ,resultSet.getBigDecimal("balance")
                        ,resultSet.getBoolean("is_blocked")
                );
        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        throw new RuntimeException();
    }


    public void updateData(BigDecimal bigDecimal, long creditCardId) {
        Connection connection = ConnectionData.getConnection();
        CreditCard creditCard = new CreditCardDAOImpl()
                .getCreditCardById(creditCardId)
                .orElseThrow(NumberFormatException::new);
        BigDecimal sumForUpdate = bigDecimal.add(creditCard.getBankAccount().getBalance())
                .setScale(2, RoundingMode.HALF_DOWN);
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DATA_BANK_ACCOUNT)){
            preparedStatement.setObject(1,sumForUpdate);
            preparedStatement.setObject(2,creditCard.getBankAccount().getId());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
    }


    public void setBlockOnBankAccount(long creditCardId) {
        Connection connection = ConnectionData.getConnection();
        CreditCard creditCard = new CreditCardDAOImpl()
                .getCreditCardById(creditCardId)
                .orElseThrow(NullPointerException::new);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_BLOCK)){
            preparedStatement.setObject(1, creditCard.getBankAccount().getId());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
    }

    public void unsetBlockOnBankAccount(int bankAccountId){
        Connection connection = ConnectionData.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UNSET_BLOCK)){
            preparedStatement.setObject(1, bankAccountId);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
    }

    public boolean checkAccessToCreditCardInformation(long creditCardId,int userId){
        Connection connection = ConnectionData.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCESS_TO_CREDIT_CARD)){
            preparedStatement.setObject(1,userId);
            preparedStatement.setObject(2,creditCardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int amount = resultSet.getInt(1);
            return amount > 0;

        } catch (SQLException throwable) {
            logger.error("sql error", throwable);
        }finally {
            ConnectionData.returnConnection(connection);
        }
        return false;
    }
}

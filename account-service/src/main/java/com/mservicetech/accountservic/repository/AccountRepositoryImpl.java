package com.mservicetech.accountservic.repository;

import com.mservicetech.accountservic.model.Account;
import com.mservicetech.accountservic.model.Transaction;
import com.networknt.db.GenericDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositoryImpl implements  AccountRepository{
    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);
    private static  final  String GET_ACCOUNT_BY_ID = "getAccountById";

    private static  final  String UPDATE_ACCOUNT_BY_ID = "updateAccountById";
    private static  final  String CREATE_TRANSACTION = "createTransaction";

    private DataSource dataSource;

    public AccountRepositoryImpl(GenericDataSource genericDataSource) {
        dataSource = genericDataSource.getDataSource();
    }

    public Account getAccountById(Long accountId) throws SQLException {
        Account account = null;
        try(Connection connection = dataSource.getConnection()){
            try (final PreparedStatement statement = connection.prepareStatement(getQueryString(GET_ACCOUNT_BY_ID))) {
                statement.setLong(1, accountId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    account = new Account();
                    account.setAccountId(accountId);
                    account.setAccountName(rs.getString("account_name"));
                    account.setAccountBalance(rs.getBigDecimal("account_balance"));
                }
            }
        }
        return account;
    }

    public void resetAccount(Long accountId, Account account) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            try (final PreparedStatement statement = connection.prepareStatement(getQueryString(UPDATE_ACCOUNT_BY_ID))) {
                statement.setBigDecimal(1, account.getAccountBalance());
                statement.setLong(2, accountId);
                int rec = statement.executeUpdate();
                if (rec<1) {
                    logger.error("no account been reset for Id:" + accountId);
                }
            }
        }
    }


    public void createTransaction(Transaction transaction) throws Exception {
        Account account = getAccountById(transaction.getAccountId());
        if (Transaction.TransactioTypeEnum.DEPOSIT.equals(transaction.getTransactioType())) {
            account.setAccountBalance(account.getAccountBalance().add(transaction.getAmount()));
        } else {
            account.setAccountBalance(account.getAccountBalance().subtract(transaction.getAmount()));
            if (account.getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Balance less than 0, need rollback transaction");
            }
        }
        try(Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);
            try (final PreparedStatement createstatement = connection.prepareStatement(getQueryString(CREATE_TRANSACTION))) {
                createstatement.setLong(1, transaction.getAccountId());
                createstatement.setString(2, transaction.getTransactioType().name());
                createstatement.setBigDecimal(3, transaction.getAmount());
                createstatement.executeUpdate();
            }
            try (final PreparedStatement statement = connection.prepareStatement(getQueryString(UPDATE_ACCOUNT_BY_ID))) {
                statement.setBigDecimal(1, transaction.getAmount());
                statement.setLong(2, transaction.getAccountId());
                int rec = statement.executeUpdate();
                if (rec<1) {
                    logger.error("Account update by transaction error for :" + transaction.getAccountId());
                    throw  new SQLException("Account update by transaction error for :" + transaction.getAccountId());
                }
            }
            connection.commit();
        }
    }
}

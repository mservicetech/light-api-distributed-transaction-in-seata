package com.mservicetech.accountservic.repository;

import com.mservicetech.accountservic.model.Account;
import com.networknt.db.GenericDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositoryImpl implements  AccountRepository{
    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);
    private static  final  String GET_ACCOUNT_BY_ID = "getAccountById";

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
            }  catch (SQLException e) {
                logger.error("SqlException:", e);
                //TODO
            }
        }
        return account;
    }
}

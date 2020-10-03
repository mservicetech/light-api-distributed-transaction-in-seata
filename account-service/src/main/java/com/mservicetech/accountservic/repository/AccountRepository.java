package com.mservicetech.accountservic.repository;

import com.mservicetech.accountservic.AccountConfig;
import com.mservicetech.accountservic.model.Account;
import com.mservicetech.accountservic.model.Transaction;
import com.networknt.config.Config;

import java.sql.SQLException;

public interface AccountRepository {

    AccountConfig accountConfig = (AccountConfig) Config.getInstance().getJsonObjectConfig(AccountConfig.CONFIG_NAME, AccountConfig.class);

    Account getAccountById(Long accountId) throws SQLException;

    void resetAccount(Long accountId, Account account) throws SQLException;

    void createTransaction(Transaction transaction) throws Exception;

    default String getQueryString (String queryName) {
        return accountConfig.getQueryMap().get(queryName);
    }
}

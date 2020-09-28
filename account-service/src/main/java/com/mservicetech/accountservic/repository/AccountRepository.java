package com.mservicetech.accountservic.repository;

import com.mservicetech.accountservic.AccountConfig;
import com.mservicetech.accountservic.model.Account;
import com.networknt.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public interface AccountRepository {

    AccountConfig accountConfig = (AccountConfig) Config.getInstance().getJsonObjectConfig(AccountConfig.CONFIG_NAME, AccountConfig.class);

    Account getAccountById(Long accountId) throws SQLException;

    default String getQueryString (String queryName) {
        return accountConfig.getQueryMap().get(queryName);
    }
}
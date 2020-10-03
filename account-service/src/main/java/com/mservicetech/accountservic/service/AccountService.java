package com.mservicetech.accountservic.service;

import com.mservicetech.accountservic.model.Account;
import com.mservicetech.accountservic.model.Transaction;
import com.networknt.exception.ApiException;

public interface AccountService {

    Account getAccountDetailById(Long accountId) throws ApiException;

    void resetAccount(Long accountId, Account account) throws ApiException;

    void createTransaction(Transaction transaction) throws Exception;
}

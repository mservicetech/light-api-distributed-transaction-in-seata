package com.mservicetech.accountservic.service;

import com.mservicetech.accountservic.model.Account;
import com.mservicetech.accountservic.model.Transaction;
import com.mservicetech.accountservic.repository.AccountRepository;
import com.networknt.exception.ApiException;
import com.networknt.service.SingletonServiceFactory;
import com.networknt.status.Status;

import java.sql.SQLException;

public class AccountServiceImpl  implements  AccountService{
    private AccountRepository accountRepository = (AccountRepository) SingletonServiceFactory.getBean(AccountRepository.class);

    public  Account getAccountDetailById(Long accountId) throws ApiException  {
        Account account = null;
        try {
            account = accountRepository.getAccountById(accountId);
        } catch (SQLException e) {
           Status status = new Status("ERR10017");
           throw new ApiException(status);
        }
        return account;
    }

    public void  resetAccount(Long accountId, Account account) throws ApiException {
        try {
            accountRepository.resetAccount(accountId, account);
        } catch (SQLException e) {
            Status status = new Status("ERR10017");
            throw new ApiException(status);
        }
    }

    public void createTransaction(Transaction transaction) throws Exception {
        try {
            accountRepository.createTransaction(transaction);
        } catch (Exception e) {
            Status status = new Status("ERR10017");
            throw new ApiException(status);
        }
    }
}

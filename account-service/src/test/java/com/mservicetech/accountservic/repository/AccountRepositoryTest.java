package com.mservicetech.accountservic.repository;

import com.mservicetech.accountservic.model.Account;
import com.networknt.service.SingletonServiceFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class AccountRepositoryTest {

    private AccountRepository accountRepository = (AccountRepository) SingletonServiceFactory.getBean(AccountRepository.class);

    @BeforeClass
    public static void setUp() {

    }

    @Test
    public void testGetAccountById() throws SQLException {
        Account account = accountRepository.getAccountById(1L);
        assertNotNull(account);
    }
}

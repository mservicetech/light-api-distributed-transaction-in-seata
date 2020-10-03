package com.mservicetech.accountservic.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mservicetech.accountservic.model.Account;
import com.mservicetech.accountservic.model.Transaction;
import com.mservicetech.accountservic.service.AccountService;
import com.networknt.body.BodyHandler;
import com.networknt.config.Config;
import com.networknt.exception.ApiException;
import com.networknt.handler.LightHttpHandler;
import com.networknt.service.SingletonServiceFactory;
import io.undertow.server.HttpServerExchange;

import io.undertow.util.HttpString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountsTransactionsPostHandler implements LightHttpHandler {
    private static final Logger logger = LoggerFactory.getLogger(AccountsTransactionsPostHandler.class);
    private static final ObjectMapper objectMapper = Config.getInstance().getMapper();
    private static AccountService accountService = SingletonServiceFactory.getBean(AccountService.class);

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Transaction transaction = objectMapper.convertValue(exchange.getAttachment(BodyHandler.REQUEST_BODY), Transaction.class);
        try {
            accountService.createTransaction(transaction);
            exchange.getResponseHeaders().add(new HttpString("Content-Type"), "application/json");
            exchange.setStatusCode(200);
            exchange.getResponseSender().send("{Transaction created}");
        } catch (ApiException e) {
            logger.error("Error Occurred: " + e.getMessage());
            setExchangeStatus(exchange, e.getStatus());
            exchange.getResponseSender().send(e.getMessage());
        }
    }
}

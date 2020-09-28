package com.mservicetech.accountservic.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.config.Config;
import com.networknt.handler.LightHttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountsTransactionsPostHandler implements LightHttpHandler {
    private static final Logger logger = LoggerFactory.getLogger(AccountsTransactionsPostHandler.class);
    private static final ObjectMapper objectMapper = Config.getInstance().getMapper();
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.endExchange();
    }
}

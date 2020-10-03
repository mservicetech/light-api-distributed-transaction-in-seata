
package com.mservicetech.accountservic.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mservicetech.accountservic.model.Transaction;
import com.networknt.client.Http2Client;
import com.networknt.config.Config;
import com.networknt.exception.ClientException;
import com.networknt.openapi.ResponseValidator;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.status.Status;
import com.networknt.utility.StringUtils;
import io.undertow.UndertowOptions;
import io.undertow.client.ClientConnection;
import io.undertow.client.ClientRequest;
import io.undertow.client.ClientResponse;
import io.undertow.util.HeaderValues;
import io.undertow.util.HttpString;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnio.IoUtils;
import org.xnio.OptionMap;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;



public class AccountsTransactionsPostHandlerTest {
    @ClassRule
    public static TestServer server = TestServer.getInstance();

    static final Logger logger = LoggerFactory.getLogger(AccountsTransactionsPostHandlerTest.class);
    static final boolean enableHttp2 = server.getServerConfig().isEnableHttp2();
    static final boolean enableHttps = server.getServerConfig().isEnableHttps();
    static final int httpPort = server.getServerConfig().getHttpPort();
    static final int httpsPort = server.getServerConfig().getHttpsPort();
    static final String url = enableHttp2 || enableHttps ? "https://localhost:" + httpsPort : "http://localhost:" + httpPort;
    static final String JSON_MEDIA_TYPE = "application/json";

    @Test
    public void testAccountsTransactionsPostHandlerTest() throws ClientException {

        final Http2Client client = Http2Client.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);
        final ClientConnection connection;
        try {
            connection = client.connect(new URI(url), Http2Client.WORKER, Http2Client.SSL, Http2Client.BUFFER_POOL, enableHttp2 ? OptionMap.create(UndertowOptions.ENABLE_HTTP2, true): OptionMap.EMPTY).get();
        } catch (Exception e) {
            throw new ClientException(e);
        }
        final AtomicReference<ClientResponse> reference = new AtomicReference<>();
        String requestUri = "/v1/accounts/transactions";
        String httpMethod = "post";
        try {
            ClientRequest request = new ClientRequest().setPath(requestUri).setMethod(Methods.POST);
            Transaction transaction = new Transaction();
            transaction.setAccountId(1L);
            transaction.setTransactioType(Transaction.TransactioTypeEnum.DEPOSIT);
            transaction.setAmount(new BigDecimal(20));
            ObjectMapper objectMapper = Config.getInstance().getMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String str = objectMapper.writeValueAsString(transaction);
            System.out.println("json:" + str);
            request.getRequestHeaders().put(Headers.CONTENT_TYPE, JSON_MEDIA_TYPE);
            request.getRequestHeaders().put(Headers.TRANSFER_ENCODING, "chunked");
            //customized header parameters 
            connection.sendRequest(request, client.createClientCallback(reference, latch, str));
            
            latch.await();
        } catch (Exception e) {
            logger.error("Exception: ", e);
            throw new ClientException(e);
        } finally {
            IoUtils.safeClose(connection);
        }
        String body = reference.get().getAttachment(Http2Client.RESPONSE_BODY);
        System.out.println("body:" + body);
        Optional<HeaderValues> contentTypeName = Optional.ofNullable(reference.get().getResponseHeaders().get(Headers.CONTENT_TYPE));
        SchemaValidatorsConfig config = new SchemaValidatorsConfig();
        ResponseValidator responseValidator = new ResponseValidator(config);
        int statusCode = reference.get().getResponseCode();
        Assert.assertEquals(statusCode, 200);
    }
}


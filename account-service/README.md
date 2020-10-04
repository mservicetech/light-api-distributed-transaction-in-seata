# Account service API

This ia account service API in the sample project. The API provide endpoints for create transaction / reset account and display account detail:

Endpoints info:


-- /v1/accounts/transactions        create transaction, this endpoint will create a transaction to transaction table and process account balance based on transaction type and amount

-- /v1/accounts/{accountId}        get account detail to display by account id

-- /v1/accounts/{accountId}/reset     reset sepcified account to the input account amount 


The account service use mysql as transactional database for API and use H2 database for junit test.

if user try to start the service logic without using docker, then we need run the db script in the seate-demo-db in the local my mysql database:

  -- setup-account-db.sql
  
And we need verify the datasource setting in the values.yml file:


```
datasource.MysqlDataSource:
  DriverClassName: com.mysql.jdbc.Driver
  jdbcUrl: jdbc:mysql://localhost:3308/account_db?useSSL=false
  username: account_user
  password: CRYPT:odPqWOazjDxeVcOU3j0YCc2+LdwfgiJmoFcWTSoKRUw=
  maximumPoolSize: 2
  connectionTimeout: 5000
  parameters:
    useServerPrepStmts: 'true'
    cachePrepStmts: 'true'
    cacheCallableStmts: 'true'
    prepStmtCacheSize: '4096'
    prepStmtCacheSqlLimit: '2048'
    verifyServerCertificate: 'false'
    useSSL: 'true'
    requireSSL: 'true'  


```

## Build and Start

For testing locally, you don't need to create the artifact for the document, source code, and the fatjar. You can build and start the server with the following command.

```
mvn clean install exec:exec
```

or

```
mvn clean package exec:exec
```

If you want to build the fatjar and other artifacts, please use the following command.

```
mvn clean install -Prelease
```

With the fatjar in the target directory, you can start the server with the following command.

```
java -jar target/account-service-seata-3.0.1.jar
```

## Test

-- get account detail:

```
curl -k --location --request GET 'https://localhost:8441/v1/accounts/1' \
--header 'Content-Type: application/json' \
```

-- reset account 

```
curl -k --location --request PUT 'https://localhost:8441/v1/accounts/1/reset' \
--header 'Content-Type: application/json' \
--data-raw '{"accountBalance":200,"accountId":1,"accountName":"test"}'
```

-- create transaction

```
curl -k --location --request POST 'https://localhost:8441/v1/accounts/transactions' \
--header 'Content-Type: application/json' \
--data-raw '{"accountId":1,"transactioType":"DEPOSIT","amount":20}'
```
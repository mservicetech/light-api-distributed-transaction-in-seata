FROM mysql:8.0

COPY ./setup-order-db.sql /docker-entrypoint-initdb.d/setup-order-db.sql
COPY setup-inventory-db.sql /docker-entrypoint-initdb.d/setup-inventory-db.sql
COPY setup-account-db.sql /docker-entrypoint-initdb.d/setup-account-db.sql
COPY ./setup-seata-server-db.sql /docker-entrypoint-initdb.d/setup-seata-server-db.sql

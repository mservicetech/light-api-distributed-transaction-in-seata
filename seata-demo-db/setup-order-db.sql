-- CREATE USER 'zpc'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'order_user'@'%' identified by 'passWord00';

CREATE DATABASE IF NOT EXISTS order_db;
GRANT ALL PRIVILEGES ON order_db.* to 'order_user';
GRANT ALL PRIVILEGES ON order_db.* to 'zpc';

use order_db;

CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `prot_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) NOT NULL,
  `order_date` timestamp default current_timestamp NOT NULL,
  `total_price` decimal(10, 2) not null,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `prot_order_item` (
  `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `prod_id`  bigint(20) NOT NULL,
  `order_quantity` int(10) default 1 NOT NULL,
  `order_unit_price`   decimal(10, 2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `ORDER_ORDER_ID` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- DML

-- CREATE USER 'zpc'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'inventory_user'@'%' identified by 'passWord00';

CREATE DATABASE IF NOT EXISTS inventory_db;
GRANT ALL PRIVILEGES ON inventory_db.* to 'inventory_user';
GRANT ALL PRIVILEGES ON inventory_db.* to 'zpc';

use inventory_db;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `prot_catalog` (
  `cat_code` varchar(128) NOT NULL,
  `cat_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`cat_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `prot_product` (
  `prod_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cat_code` varchar(128) DEFAULT NULL,
  `prod_name` varchar(256) NOT NULL,
  PRIMARY KEY (`prod_id`),
  KEY `catalog_cat_code` (`cat_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `prot_inventory` (
  `prod_id` bigint(20) NOT NULL,
  `prod_quantity` int(10) NOT NULL,
  `prod_unit_price`   decimal(10, 2) NOT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- DML
insert into prot_catalog (cat_code, cat_name) values
    ('fruit', 'Fruit Catalog'),
    ('veg', 'Vegetable Catalog');
insert into prot_product (cat_code, prod_name) values
    ('fruit', 'apple'),
    ('fruit', 'banana'),
    ('fruit', 'pear');
insert into prot_inventory (prod_id, prod_quantity, prod_unit_price) values
    ((select prod_id from prot_product where cat_code='fruit' and prod_name='apple'), 60, 1.00),
    ((select prod_id from prot_product where cat_code='fruit' and prod_name='banana'), 60, 2.00),
    ((select prod_id from prot_product where cat_code='fruit' and prod_name='pear'), 60, 3.00);

-- CREATE USER 'zpc'@'%' identified by 'N0Passw0rd'; 

CREATE USER 'inventory_user'@'%' identified by 'passWord00';

CREATE DATABASE IF NOT EXISTS inventory_db;
GRANT ALL PRIVILEGES ON inventory_db.* to 'inventory_user';

use inventory_db;

CREATE TABLE undo_log (
  id  bigint(20) NOT NULL AUTO_INCREMENT,
   branch_id  bigint(20) NOT NULL,
   xid  varchar(100) NOT NULL,
   context  varchar(128) NOT NULL,
   rollback_info  longblob NOT NULL,
   log_status  int(11) NOT NULL,
   log_created  datetime NOT NULL,
   log_modified  datetime NOT NULL,
   ext  varchar(100) DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  ux_undo_log  ( xid , branch_id )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



CREATE TABLE inventory (
  prod_id bigint(20) NOT NULL,
  prod_quantity int(10) NOT NULL,
  prod_name varchar(256) NOT NULL,
  prod_unit_price   decimal(10, 2) NOT NULL,
  last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (prod_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- DML
insert into inventory (prod_id, prod_name, prod_quantity, prod_unit_price) values(1,'apple', 60, 1.00);
insert into inventory (prod_id, prod_name, prod_quantity, prod_unit_price) values(2,'banana', 60, 2.00);
insert into inventory (prod_id, prod_name, prod_quantity, prod_unit_price) values(3,'pear', 60, 3.00);


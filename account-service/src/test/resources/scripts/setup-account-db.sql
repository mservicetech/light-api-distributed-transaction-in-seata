
CREATE TABLE undo_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  branch_id bigint(20) NOT NULL,
  xid varchar(100) NOT NULL,
  context varchar(128) NOT NULL,
  rollback_info longblob NOT NULL,
  log_status int(11) NOT NULL,
  log_created datetime NOT NULL,
  log_modified datetime NOT NULL,
  ext varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ux_undo_log (xid,branch_id)
);

CREATE TABLE account (
  account_id bigint(20) NOT NULL AUTO_INCREMENT,
  account_name varchar(256) not null,
  account_balance decimal(10, 2) not null,
  PRIMARY KEY (account_id)
) ;

CREATE TABLE transaction (
  tx_id bigint(20) NOT NULL AUTO_INCREMENT,
  account_id bigint(20) NOT NULL,
  tx_date  TIMESTAMP default CURRENT_TIMESTAMP NOT NULL,
  tx_type VARCHAR(16) NOT NULL,
  tx_amount decimal(10, 2) NOT NULL,
--  ref_id   varchar(96) NOT NULL,
  PRIMARY KEY (tx_id),
  KEY account_account_id (account_id)
) ;

insert into account (account_name, account_balance)
values ('Yan Chen', 100)
;

insert into account (account_name, account_balance)
values ('Ken Chen', 200)
;
DROP TABLE IF EXISTS m_user;

CREATE TABLE m_user (
  	user_id          	VARCHAR(32)   PRIMARY KEY
,	password         	VARCHAR(255)  NOT NULL
,	role              	VARCHAR(15)   NOT NULL
,	is_account_enabled	BOOLEAN       NOT NULL
,	last_name       	VARCHAR(50)   NOT NULL
,	middle_name			VARCHAR(50)   
,	first_name        	VARCHAR(50)   NOT NULL
,	last_name_kana  	VARCHAR(50)   NOT NULL
,	middle_name_kana	VARCHAR(50)
,	first_name_kana   	VARCHAR(50)   NOT NULL
,	mail              	VARCHAR(255)  UNIQUE
,	job_post_cd     	VARCHAR(3)
,	extension_tel     	VARCHAR(50)
,	mobile_tel        	VARCHAR(50)
,	birthday          	DATE
, 	gender            	VARCHAR(1)
,	hire_date         	DATE
,	add_date          	TIMESTAMP     NOT NULL
,	upd_date          	TIMESTAMP     NOT NULL
,	last_login_date   	TIMESTAMP 
);
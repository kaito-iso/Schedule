DROP TABLE IF EXISTS m_user;

CREATE TABLE m_user (
  	user_id          	 VARCHAR(32)   PRIMARY KEY
,	password         	 VARCHAR(255)  NOT NULL
,	role              	 VARCHAR(15)   NOT NULL
,	is_account_enabled   BOOLEAN       NOT NULL
--,	family_name       VARCHAR(50)   NOT NULL
--,	given_name        VARCHAR(50)   NOT NULL
--,	family_name_kana  VARCHAR(50)   NOT NULL
--,	given_name_kana   VARCHAR(50)   NOT NULL
--,	mail              VARCHAR(255)  UNIQUE
--,	position_cd       VARCHAR(3)
--,	extension_tel     VARCHAR(50)
--,	mobile_tel        VARCHAR(50)
--,	birthday          DATE
--, gender            VARCHAR(3)
--,	hire_date         DATE
--,	resignation_date  DATE
--,	add_date          DATETIME      NOT NULL
--,	upd_date          DATETIME      NOT NULL
--,	last_login_date 	DATETIME 
);
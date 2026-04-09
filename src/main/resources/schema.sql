DROP TABLE IF EXISTS m_user;
DROP TABLE IF EXISTS m_holiday;
DROP TABLE IF EXISTS m_facility;
DROP TABLE IF EXISTS m_job_title;

-- ユーザーマスタ
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
,	last_login_date   	TIMESTAMP
,	is_active          	BOOLEAN
,	add_date          	TIMESTAMP
,	add_code			VARCHAR(32)
,	upd_date          	TIMESTAMP
,	upd_code			VARCHAR(32) 
);

-- 祝日マスタ
CREATE TABLE m_holiday(
	holiday_date		DATE		  PRIMARY KEY
,	holiday_name		VARCHAR(30)   NOT NULL
,	create_type			VARCHAR(5)	  NOT NULL
);

-- 施設マスタ
CREATE TABLE m_facility(
	facility_code		VARCHAR(5)    PRIMARY KEY
,	facility_name		VARCHAR(50)	  NOT NULL
,	display_order		INT			  NOT NULL
,	is_active          	BOOLEAN		  NOT NULL
,	add_date			TIMESTAMP	  NOT NULL
,	add_code			VARCHAR(32)   NOT NULL
,	upd_date			TIMESTAMP
,	upd_code			VARCHAR(32)
);

-- 役職マスタ
CREATE TABLE m_job_title(
	job_title_code		VARCHAR(5)	  PRIMARY KEY
,	job_title_name		VARCHAR(50)	  NOT NULL
,	display_order		INT			  NOT NULL
,	is_active          	BOOLEAN		  NOT NULL
,	add_date			TIMESTAMP	  NOT NULL
,	add_code			VARCHAR(32)   NOT NULL
,	upd_date			TIMESTAMP
,	upd_code			VARCHAR(32)
);

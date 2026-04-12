DROP TABLE IF EXISTS m_user;
DROP TABLE IF EXISTS m_holiday;
DROP TABLE IF EXISTS m_facility;
DROP TABLE IF EXISTS m_job_title;
DROP TABLE IF EXISTS m_schedule_category;
DROP TABLE IF EXISTS t_schedule;
DROP TABLE IF EXISTS t_schedule_participant;
DROP TABLE IF EXISTS t_schedule_facilitie;
DROP TABLE IF EXISTS m_year;

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
,	job_title_code     	VARCHAR(3)
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
	job_title_code		VARCHAR(3)	  PRIMARY KEY
,	job_title_name		VARCHAR(50)	  NOT NULL
,	display_order		INT			  NOT NULL
,	is_active          	BOOLEAN		  NOT NULL
,	add_date			TIMESTAMP	  NOT NULL
,	add_code			VARCHAR(32)   NOT NULL
,	upd_date			TIMESTAMP
,	upd_code			VARCHAR(32)
);

-- スケジュールカテゴリー
CREATE TABLE m_schedule_category(
	category_code		VARCHAR(5)	  PRIMARY KEY
,	category_name		VARCHAR(50)	  NOT NULL
,	category_color		VARCHAR(7) 	  NOT NULL
,	display_order		INT			  NOT NULL
,	is_active          	BOOLEAN		  NOT NULL
,	add_date			TIMESTAMP	  NOT NULL
,	add_code			VARCHAR(32)   NOT NULL
,	upd_date			TIMESTAMP
,	upd_code			VARCHAR(32)
);

-- スケジュール
CREATE TABLE t_schedule(
	id					BIGINT		  PRIMARY KEY 
,	category_code		VARCHAR(5)
,	title				VARCHAR(100)
,	content				TEXT
,	start_date			TIMESTAMP	  NOT NULL
,	end_date			TIMESTAMP	  NOT NULL
,	is_all_day			BOOLEAN		  NOT NULL
,	is_public			BOOLEAN		  NOT NULL
,	meeting_url			VARCHAR(500)
,	recurrence_rule		TEXT
,	recurrence_id		BIGINT
,	add_date			TIMESTAMP	  NOT NULL
,	add_code			VARCHAR(32)	  NOT NULL
,	upd_date			TIMESTAMP
,	upd_code			VARCHAR(32)
);

-- スケジュール参加者
CREATE TABLE t_schedule_participant(
	schedule_id 		BIGINT 		  NOT NULL
,	user_id     		VARCHAR(32)   NOT NULL
,	PRIMARY KEY (schedule_id, user_id)
);

-- スケジュール施設
CREATE TABLE t_schedule_facilitie(
	schedule_id 		BIGINT		  NOT NULL
,	facility_code 		VARCHAR(5) 	  NOT NULL
,	PRIMARY KEY (schedule_id, facility_code)
);

-- スケジュール年
CREATE TABLE m_year(
	"year"				INT 		  PRIMARY KEY 
,	era_name			VARCHAR(10)
,	era_year			INT 
,	is_active			BOOLEAN		  NOT NULL
);

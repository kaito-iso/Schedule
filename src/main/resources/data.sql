DELETE FROM m_user;

INSERT INTO m_user (
	user_id
, 	password
,	role
,	is_account_enabled
,	last_name
,	middle_name
,	first_name
,	last_name_kana
,	middle_name_kana
,	first_name_kana
,	mail
,	job_title_code
,	extension_tel
,	mobile_tel
,	birthday
, 	gender
,	hire_date
,	add_date
,	upd_date
,	last_login_date
,	is_active
)
VALUES (
	'user'
,	'$2a$10$uQOAxGqDZ05J56SrBzyld.S2mWNBFJHmgesf.JRIAPktylbn2WgB.'
,	'ROLE_USER'
,	true
,	'苗字user'
,	null
,	'名前user'
,	'ミョウジ'
,	null
,	'ナマエ'
,	'user@gmail.com'
,	'001'
,	'725'
,	'090-1234-5678'
,	'2000-01-01'
,	'M'
,	'2020-01-01'
,	'2025-01-01'
,	'2025-01-01'
,	null
,	true
);

INSERT INTO m_user (
	user_id
, 	password
,	role
,	is_account_enabled
,	last_name
,	middle_name
,	first_name
,	last_name_kana
,	middle_name_kana
,	first_name_kana
,	mail
,	job_title_code
,	extension_tel
,	mobile_tel
,	birthday
, 	gender
,	hire_date
,	add_date
,	upd_date
,	last_login_date
,	is_active
)
VALUES (
	'admin'
,	'$2a$10$Y13qO8OpQFRyQckvBASWOulhOvQ2fMPkEq3Umch.kiTplvjbXxTOq'
,	'ROLE_ADMIN'
,	true
,	'山田'
,	null
,	'太郎'
,	'ミョウジ'
,	null
,	'ナマエ'
,	'admin@gmail.com'
,	'001'
,	'725'
,	'090-1234-5678'
,	'2000-01-01'
,	'M'
,	'2020-01-01'
,	'2025-01-01'
,	'2025-01-01'
,	null
,	true
);

INSERT INTO m_facility(facility_code,facility_name,display_order,is_active,add_date,add_code)
VALUES ('HNSYA','本社',1,true,'2025-01-01 12:00:00','admin');
INSERT INTO m_facility(facility_code,facility_name,display_order,is_active,add_date,add_code)
VALUES ('TOKYO','東京支社',2,true,'2025-01-01 12:00:00','admin');
INSERT INTO m_facility(facility_code,facility_name,display_order,is_active,add_date,add_code)
VALUES ('OSAKA','大阪支社',3,true,'2025-01-01 12:00:00','admin');

INSERT INTO m_job_title(job_title_code,job_title_name,display_order,is_active,add_date,add_code)
VALUES ('000','会長',1,true,'2025-01-01 12:00:00','admin');
INSERT INTO m_job_title(job_title_code,job_title_name,display_order,is_active,add_date,add_code)
VALUES ('010','社長',2,true,'2025-01-01 12:00:00','admin');
INSERT INTO m_job_title(job_title_code,job_title_name,display_order,is_active,add_date,add_code)
VALUES ('900','一般社員',3,true,'2025-01-01 12:00:00','admin');

INSERT INTO m_schedule_category(category_code,category_name,category_color,display_order,is_active,add_date,add_code)
VALUES('KAIGI','会議','#1e90ff',1,true,'2026-04-01 12:00:00','admin');
INSERT INTO m_schedule_category(category_code,category_name,category_color,display_order,is_active,add_date,add_code)
VALUES('RAIHO','来訪','#00ff7f',2,true,'2026-04-01 12:00:00','admin');
INSERT INTO m_schedule_category(category_code,category_name,category_color,display_order,is_active,add_date,add_code)
VALUES('SYTYO','出張','#ffb6c1',3,true,'2026-04-01 12:00:00','admin');

INSERT INTO m_year("year",era_name,era_year,is_active)
VALUES(2026,'令和',8,true);
INSERT INTO m_year("year",era_name,era_year,is_active)
VALUES(2027,'令和',9,true);
INSERT INTO m_year("year",era_name,era_year,is_active)
VALUES(2028,'令和',10,true);
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
,	job_post_cd
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
,	job_post_cd
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
,	'苗字admin'
,	null
,	'名前admin'
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

INSERT INTO m_facility(
	facility_code
,	facility_name
,	display_order
,	is_active
)
VALUES (
	'HNSYA'
,	'本社'
,	1
,	true
);

INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('OSK01', '大阪支社', 2, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('NGY01', '名古屋支社', 3, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('FUK01', '福岡支社', 4, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('SAP01', '札幌支社', 5, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('OKN01', '沖縄支店', 6, true);

INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('TKY01', '東京営業所1', 7, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('TKY02', '東京営業所2', 8, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('TKY03', '東京営業所3', 9, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('TKY04', '東京営業所4', 10, true);

INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('KYT01', '京都拠点', 11, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('HYG01', '兵庫拠点', 12, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('SHG01', '滋賀拠点', 13, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('MIE01', '三重拠点', 14, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('NAG01', '長野拠点', 15, true);

INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('IBR01', '茨城営業所', 16, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('TCG01', '栃木営業所', 17, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('GNM01', '群馬営業所', 18, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('SIT01', '埼玉営業所', 19, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('CHB01', '千葉営業所', 20, true);

INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('KNG01', '神奈川営業所', 21, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('FKS01', '福島営業所', 22, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('AOM01', '青森営業所', 23, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('IWT01', '岩手営業所', 24, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('MGT01', '宮城営業所', 25, true);

INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('AKT01', '秋田営業所', 26, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('YNM01', '山形営業所', 27, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('YMG01', '山口営業所', 28, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('KMM01', '熊本営業所', 29, true);
INSERT INTO m_facility (facility_code, facility_name, display_order, is_active) VALUES ('KGS01', '鹿児島営業所', 30, true);
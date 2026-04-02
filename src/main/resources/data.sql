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
);
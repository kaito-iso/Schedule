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

INSERT INTO m_holiday (holiday_date, holiday_name,create_type,is_deleted) VALUES
('2027-01-01', '元日','api',false),
('2027-01-11', '成人の日','api',false),
('2027-02-11', '建国記念の日','api',false),
('2027-02-23', '天皇誕生日','api',false),
('2027-03-21', '春分の日','api',false),
('2027-03-22', '春分の日 振替休日','api',false),
('2027-04-29', '昭和の日','api',false),
('2027-05-03', '憲法記念日','api',false),
('2027-05-04', 'みどりの日','api',false),
('2027-05-05', 'こどもの日','api',false),
('2027-07-19', '海の日','api',false),
('2027-08-11', '山の日','api',false),
('2027-09-20', '敬老の日','api',false),
('2027-09-23', '秋分の日','api',false),
('2027-10-11', 'スポーツの日','api',false),
('2027-11-03', '文化の日','api',false),
('2027-11-23', '勤労感謝の日','api',false);
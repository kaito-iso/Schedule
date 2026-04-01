DELETE FROM m_user;

INSERT INTO m_user (
	user_id
, 	password
,	role
,	is_account_enabled
,	last_login_date
)
VALUES (
	'user'
,	'$2a$10$uQOAxGqDZ05J56SrBzyld.S2mWNBFJHmgesf.JRIAPktylbn2WgB.'
,	'ROLE_ADMIN'
,	true
,	null
);
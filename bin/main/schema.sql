CREATE TABLE USERS (
	id BINARY(16) PRIMARY KEY,
	name varchar(255) NOT NULL,
	password char(8) NOT NULL,
	email varchar(255) NOT NULL,
	created timestamp NOT NULL,
	modified timestamp NOT NULL,
	last_login timestamp NOT NULL,
	token varchar(8000) NOT NULL,
	is_active boolean default false,
	UNIQUE UNIQUE_EMAIL(email)
);

CREATE TABLE TELEPHONES (
	id BINARY(16) PRIMARY KEY,
	user_id BINARY(16) NOT NULL,
	country_code char(3),
	city_code char(3),
	number char(8)
);
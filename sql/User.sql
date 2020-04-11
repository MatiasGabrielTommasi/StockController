DROP FUNCTION IF EXISTS users_list;
DROP FUNCTION IF EXISTS users_search;
DROP FUNCTION IF EXISTS users_set_names;
DROP FUNCTION IF EXISTS users_set_surname;
DROP FUNCTION IF EXISTS users_set_phone;
DROP FUNCTION IF EXISTS users_login;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS persons;



CREATE TABLE persons(	
    id_person INTEGER PRIMARY KEY
    ,names TEXT NOT NULL
    ,surname TEXT NOT NULL
    ,phone TEXT NOT NULL
);

CREATE TABLE users 
(
	username TEXT NOT NULL
	,pass TEXT NOT NULL
) INHERITS(persons);

CREATE OR REPLACE FUNCTION users_ins(
	IN p_id_person Integer
    ,IN p_name TEXT
    ,IN p_surname TEXT
    ,IN p_phone TEXT
	,IN p_username text
	,IN p_pass text
	) RETURNS void as
$$ 
	insert into users (id_person, names, surname, phone, username, pass) values (p_id_person, p_name, p_surname, p_phone, p_username, p_pass);
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION users_del(
	IN p_id_person Integer
	) RETURNS void as
$$ 
	delete FROM users where id_person = p_id_person
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION users_list(
) RETURNS setof users as
$$
	SELECT * FROM users
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION users_search(	
    p_id_person INTEGER
    ,p_name TEXT
    ,p_surname TEXT
    ,p_phone TEXT
	,p_username TEXT
) RETURNS SETOF users

AS $$
	SELECT * FROM users
	WHERE
	(id_person = id_person or id_person = 0 or id_person IS NULL) and
	(names ilike('%' || p_name || '%') or p_name = '' or p_name IS NULL) and
	(surname ilike('%' || p_surname || '%') or p_surname = '' or p_surname IS NULL) and
	(phone ilike('%' || p_phone || '%') or p_phone = '' or p_phone IS NULL) and
	(username ilike('%' || p_username || '%') or p_username = '' or p_username IS NULL)
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION users_login(
    p_username TEXT
    ,p_pass TEXT
) RETURNS SETOF users

AS $$
	SELECT * FROM users
	WHERE
	username = p_username and pass = p_pass
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION users_set_names(
	IN p_id_person Integer
	,IN p_names TEXT
	) RETURNS void as
$$
	UPDATE users SET names = p_names where id_person = p_id_person
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION users_set_surname(
	IN p_id_person Integer
	,IN p_surname TEXT
	) RETURNS void as
$$
	UPDATE users SET surname = p_surname where id_person = p_id_person
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION users_set_phone(
	IN p_id_person Integer
	,IN p_phone TEXT
	) RETURNS void as
$$ 
	UPDATE users SET phone = p_phone where id_person = p_id_person
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION users_set_username(
	IN p_id_person Integer
	,IN p_username TEXT
	) RETURNS void as
$$
	UPDATE users SET username = p_username where id_person = p_id_person
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION users_set_pass(
	IN p_id_person Integer
	,IN p_pass TEXT
	) RETURNS void as
$$
	UPDATE users SET pass = p_pass where id_person = p_id_person
$$ LANGUAGE sql VOLATILE strict;
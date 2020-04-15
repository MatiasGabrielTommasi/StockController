
DROP FUNCTION IF EXISTS users_ins;
DROP FUNCTION IF EXISTS users_del;
DROP FUNCTION IF EXISTS users_list;
DROP FUNCTION IF EXISTS users_search;
DROP FUNCTION IF EXISTS users_login;
DROP FUNCTION IF EXISTS users_set_names;
DROP FUNCTION IF EXISTS users_set_surname;
DROP FUNCTION IF EXISTS users_set_phone;
DROP FUNCTION IF EXISTS users_set_username;
DROP FUNCTION IF EXISTS users_set_pass;
drop FUNCTION if exists tickets_ins;
drop FUNCTION if exists tickets_products_ins;
drop function if exists tickets_week;
DROP function if exists products_list;
DROP FUNCTION if exists products_list_low_stock;
DROP FUNCTION if exists products_ins;
DROP FUNCTION if exists products_del;
DROP FUNCTION if exists products_set_product_name;
DROP FUNCTION if exists products_set_price;
DROP FUNCTION if exists products_set_stock;
DROP FUNCTION if exists products_set_code;

drop table if exists tickets_products;
drop TABLE if exists tickets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS persons;
DROP table if exists products;

--select * from tickets t2 
--"SELECT * from tickets_ins(69.0, 1)"

CREATE TABLE products (
    id_product serial NOT NULL,
    product_name text NOT NULL,
    price numeric(18,2) NOT NULL,
    stock int4 NOT NULL DEFAULT 0,
    code text NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id_product)
);
--select * from products_list(0, '', 0, '')
--select * from products p 
CREATE OR REPLACE FUNCTION products_list(
    IN p_id_product INTEGER
    ,IN p_product_name TEXT
    ,IN p_stock INTEGER
    ,IN p_code TEXT
) RETURNS setof products as
$$
    SELECT id_product, product_name, price, stock, code FROM Products
    WHERE
    (id_product = p_id_product or p_id_product = 0 or p_id_product IS NULL) and
    (product_name ilike('%' || p_product_name || '%') or p_product_name = '' or p_product_name IS NULL) and
    (stock >= p_stock or p_stock = 0 or p_stock IS NULL) and
    (code = p_code or p_code = '' or p_code IS NULL)
    order by id_product asc
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION products_list_low_stock(
) returns setof products as
$$
    SELECT id_product, product_name, price, stock, code FROM Products
    WHERE
    stock <= 5
    order by id_product asc
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION products_ins(
    IN p_product_name TEXT
    ,IN p_price NUMERIC(18, 2)
    ,IN p_stock INTEGER
    ,IN p_code TEXT
) RETURNS products AS
$$
    INSERT INTO Products (product_name, price, stock, code)
    VALUES (p_product_name, p_price, p_stock, p_code)
    RETURNING *;
$$ LANGUAGE sql VOLATILE;


CREATE OR REPLACE FUNCTION products_del(
    IN p_id_product INTEGER
) RETURNS void AS
$$
    DELETE FROM Products WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE;

CREATE OR REPLACE FUNCTION products_set_product_name (
    IN p_id_product INTEGER
    ,IN p_product_name TEXT
) RETURNS void AS 
$$
    UPDATE products SET product_name = p_product_name WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

CREATE OR REPLACE FUNCTION products_set_price (
    IN p_id_product INTEGER
    ,IN p_price NUMERIC(18, 2)
) RETURNS void AS 
$$
    UPDATE products SET price = p_price WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

CREATE OR REPLACE FUNCTION products_set_stock (
    IN p_id_product INTEGER
    ,IN p_stock INTEGER
) RETURNS void AS 
$$
    UPDATE products SET stock = p_stock WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

CREATE OR REPLACE FUNCTION products_set_code (
    IN p_id_product INTEGER
    ,IN p_code TEXT
) RETURNS void AS 
$$
    UPDATE products SET code = p_code WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;










CREATE TABLE persons(   
    id_person INTEGER PRIMARY KEY
    ,names TEXT NOT NULL
    ,surname TEXT NOT NULL
    ,phone TEXT NOT NULL
);


CREATE TABLE users (
    username text NOT NULL,
    pass text NOT null,
  CONSTRAINT pk_users PRIMARY KEY (id_person)
)INHERITS (persons);

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
    (id_person = p_id_person or p_id_person = 0 or p_id_person IS NULL) and
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



CREATE TABLE tickets(
    id_ticket SERIAL PRIMARY KEY
    ,ticket_date DATE NOT NULL DEFAULT CURRENT_DATE
    ,mount NUMERIC(18, 2) NOT NULL
    ,id_user INTEGER NOT NULL REFERENCES users(id_person)
);

CREATE TABLE tickets_products(
    id_ticket INTEGER NOT NULL REFERENCES tickets(id_ticket)
    ,id_product INTEGER NOT NULL REFERENCES products(id_product)
    ,price NUMERIC(18, 2) NOT NULL
    ,quantity INTEGER NOT NULL
);

CREATE OR REPLACE FUNCTION tickets_ins(
    IN p_mount NUMERIC(18, 2)
    ,IN p_id_user INTEGER
	) RETURNS void as
$$
	insert into tickets (mount, id_user) values (p_mount, p_id_user);
$$ LANGUAGE sql VOLATILE strict;

CREATE OR REPLACE FUNCTION tickets_products_ins(
    IN p_id_ticket INTEGER
    ,IN p_id_product INTEGER
    ,IN p_price NUMERIC(18, 2)
    ,IN p_quantity INTEGER
	) RETURNS void as
$$ 
	insert into tickets_products (id_ticket, id_product, price, quantity) values (p_id_ticket, p_id_product, p_price, p_quantity);
$$ LANGUAGE sql VOLATILE strict;


CREATE OR REPLACE FUNCTION tickets_week(
) returns setof tickets as
$$
    select *
	from tickets t 
	where t.ticket_date between	
	(current_date - (extract(dow from current_date)::integer)) and 
	(current_date + (extract(dow from current_date + 1)::integer)) 
$$ LANGUAGE sql STABLE STRICT;

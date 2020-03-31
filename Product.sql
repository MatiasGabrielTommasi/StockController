CREATE TABLE Products(
    id_product SERIAL PRIMARY KEY
    ,product_name TEXT NOT NULL
    ,price NUMERIC(18, 2) NOT NULL
    ,stock INTEGER NOT NULL DEFAULT 0
    ,code TEXT NOT NULL
)
CREATE OR REPLACE FUNCTION products_ins(
    IN p_product_name TEXT
    ,IN p_price NUMERIC(18, 2)
    ,IN p_stock INTEGER
    ,IN code TEXT
) RETURNS product AS
$$
    INSERT INTO Products (product_name, price, stock, code)
    VALUES (p_product_name, p_price, p_stock, p_code);
    RETURNING *
$$ LANGUAGE sql VOLATILE;


CREATE OR REPLACE FUNCTION products_del(
    IN p_id_product TEXT
) RETURNS void AS
$$
    DELETE FROM Products WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE;


CREATE OR REPLACE FUNCTION product_get_id_product (
	IN p_id_product INTEGER
) RETURNS INTEGER AS
$$ 
	SELECT id_product FROM products WHERE id_product = p_id_product;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION products_get_product_name (
	IN p_id_product INTEGER
) RETURNS TEXT AS
$$ 
	SELECT product_name FROM products WHERE id_product = p_id_product;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION products_set_product_name (
	IN p_id_product INTEGER
	IN p_product_name TEXT
) RETURNS void AS 
$$
	UPDATE products SET product_name = p_product_name WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

CREATE OR REPLACE FUNCTION products_get_price (
	IN p_id_product INTEGER
) RETURNS NUMERIC(18, 2) AS
$$ 
	SELECT price FROM products WHERE id_product = p_id_product;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION products_set_price (
	IN p_id_product INTEGER
	IN p_price NUMERIC(18, 2)
) RETURNS void AS 
$$
	UPDATE products SET price = p_price WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

CREATE OR REPLACE FUNCTION products_get_stock (
	IN p_id_product INTEGER
) RETURNS INTEGER AS
$$ 
	SELECT stock FROM products WHERE id_product = p_id_product;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION products_set_stock (
	IN p_id_product INTEGER
	IN p_stock TEXT
) RETURNS void AS 
$$
	UPDATE products SET stock = p_stock WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

CREATE OR REPLACE FUNCTION products_get_code (
	IN p_id_product INTEGER
) RETURNS TEXT AS
$$ 
	SELECT code FROM products WHERE id_product = p_id_product;
$$ LANGUAGE sql STABLE STRICT;


CREATE OR REPLACE FUNCTION products_set_code (
	IN p_id_product INTEGER
	IN p_code TEXT
) RETURNS void AS 
$$
	UPDATE products SET code = p_code WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

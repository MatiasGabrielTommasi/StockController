CREATE OR REPLACE FUNCTION products_list(
	IN p_id_product INTEGER
	,IN p_product_name TEXT
	,IN p_stock INTEGER
	,IN p_code TEXT
) RETURNS products as
$$
	SELECT id_product, product_name, price, stock, code FROM Products
	WHERE
	(id_product = p_id_product or p_id_product = 0 or p_id_product IS NULL) and
	(product_name = p_product_name or p_product_name = '' or p_product_name IS NULL) and
	(stock = p_stock or p_stock = 0 or p_stock IS NULL) and
	(code = p_code or p_code = '' or p_code IS NULL)
$$ LANGUAGE sql STABLE STRICT;

CREATE OR REPLACE FUNCTION products_list_low_stock(
) returns setof products as
$$
	SELECT id_product, product_name, price, stock, code FROM Products
	WHERE
	stock <= 5
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
	,IN p_product_name TEXT
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
	,IN p_price NUMERIC(18, 2)
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
	,IN p_stock INTEGER
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
	,IN p_code TEXT
) RETURNS void AS 
$$
	UPDATE products SET code = p_code WHERE id_product = p_id_product;
$$ LANGUAGE sql VOLATILE STRICT;

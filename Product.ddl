CREATE TABLE Products(
    id_product SERIAL PRIMARY KEY
    ,product_name TEXT NOT NULL
    ,price NUMERIC(18, 2) NOT NULL
    ,stock INTEGER NOT NULL DEFAULT 0
    ,code TEXT NOT NULL
)
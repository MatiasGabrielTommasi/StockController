CREATE TABLE Persons(
    id_person SERIAL PRIMARY KEY
    ,names TEXT NOT NULL
    ,surname TEXT NOT NULL
    ,phone TEXT NOT NULL
)

CREATE TABLE Users(
    id_user SERIAL PRIMARY KEY
    ,username TEXT NOT NULL
    ,pass TEXT NOT NULL
    ,id_person INTEGER NOT NULL REFERENCES Persons(id_person)
)

CREATE TABLE Bitacore(
    id_bitacore SERIAL PRIMARY KEY
    ,table_name TEXT NOT NULL
    ,bitacore_date DATE NOT NULL DEFAULT CURRENT_DATE
    ,id_user INTEGER REFERENCES Users(id_user)
    ,id_registry INTEGER
)

CREATE TABLE Tickets(
    id_ticket SERIAL PRIMARY KEY
    ,ticket_date DATE NOT NULL DEFAULT CURRENT_DATE
    ,mount NUMERIC(18, 2) NOT NULL
    ,id_user INTEGER NOT NULL REFERENCES users(id_user)
)

CREATE TABLE Products(
    id_product SERIAL PRIMARY KEY
    ,product_name TEXT NOT NULL
    ,price NUMERIC(18, 2) NOT NULL
    ,stock INTEGER NOT NULL DEFAULT 0
    ,code TEXT NOT NULL
)

CREATE TABLE TicketsProducts(
    id_ticket INTEGER NOT NULL REFERENCES Tickets(id_ticket)
    ,id_product INTEGER NOT NULL REFERENCES Products(id_product)
    ,price NUMERIC(18, 2) NOT NULL
    ,quantity INTEGER NOT NULL
)
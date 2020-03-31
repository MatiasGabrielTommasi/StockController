CREATE TABLE Tickets(
    id_ticket SERIAL PRIMARY KEY
    ,ticket_date DATE NOT NULL DEFAULT CURRENT_DATE
    ,mount NUMERIC(18, 2) NOT NULL
    ,id_user INTEGER NOT NULL REFERENCES users(id_user)
)

CREATE TABLE TicketsProducts(
    id_ticket INTEGER NOT NULL REFERENCES Tickets(id_ticket)
    ,id_product INTEGER NOT NULL REFERENCES Products(id_product)
    ,price NUMERIC(18, 2) NOT NULL
    ,quantity INTEGER NOT NULL
)








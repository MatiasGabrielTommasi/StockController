CREATE TABLE Bitacore(
    id_bitacore SERIAL PRIMARY KEY
    ,table_name TEXT NOT NULL
    ,bitacore_date DATE NOT NULL DEFAULT CURRENT_DATE
    ,id_user INTEGER REFERENCES Users(id_user)
    ,id_registry INTEGER
)
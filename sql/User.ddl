CREATE TABLE Users(
    id_user SERIAL PRIMARY KEY
    ,username TEXT NOT NULL
    ,pass TEXT NOT NULL
    ,id_person INTEGER NOT NULL REFERENCES Persons(id_person)
)
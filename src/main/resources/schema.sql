DROP TABLE IF EXISTS db_schema.Users;
DROP SCHEMA IF EXISTS db_schema;

CREATE SCHEMA db_schema AUTHORIZATION postgres
    CREATE TABLE Users
    (
        id varchar(11) NOT NULL,
        login varchar(100) NOT NULL,
        firstName varchar(30) NOT NULL,
        lastName varchar(50) NOT NULL,
        dateOfBirth date NOT NULL,
        active boolean NOT NULL,
        PRIMARY KEY (id)
    );
GO
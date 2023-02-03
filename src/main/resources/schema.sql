/* better to use a dedicated tool like Flyway or Liquibase */
DROP TABLE IF EXISTS book;
CREATE TABLE book (
  id                  BIGSERIAL PRIMARY KEY NOT NULL,
  version             integer NOT NULL,
  created_by          varchar(255) NOT NULL,
  last_modified_by    varchar(255) NOT NULL,
  created_date        timestamp NOT NULL,
  last_modified_date  timestamp NOT NULL,
  author              varchar(255) NOT NULL,
  isbn                varchar(255) UNIQUE NOT NULL,
  title               varchar(255) NOT NULL
);
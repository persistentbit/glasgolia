SET SEARCH_PATH = 'testdb';

-- *************  DROP ALL  ********************
DROP TABLE IF EXISTS types_test;
drop type if EXISTS us_postal_code;
DROP TYPE IF EXISTS FULL_NAME;
DROP TYPE IF EXISTS ENUM_TEST;

-- ************* CREATE ALL ********************
CREATE TYPE ENUM_TEST AS ENUM ('enumval1', 'enumval2', 'enumval3');
COMMENT ON TYPE ENUM_TEST IS 'My First enum in Postgres';

CREATE TYPE FULL_NAME AS (
  first_name VARCHAR,
  last_name  VARCHAR
);

CREATE DOMAIN us_postal_code AS TEXT
  CHECK(
    VALUE ~ '^\d{5}$'
    OR VALUE ~ '^\d{5}-\d{4}$'
  );

CREATE TABLE types_test (
  id BIGSERIAL PRIMARY KEY,
  ser_small SMALLSERIAL,
  ser SERIAL,
  ser_big BIGSERIAL,
  an_integer INTEGER,
  a_bigint BIGINT,
  a_decimal_7_2 DECIMAL(7,2),
  a_numeric_6 NUMERIC(6),
  a_numeric NUMERIC,
  a_real real,
  a_double DOUBLE PRECISION,
  an_int2 int2,
  an_int4 int4,
  an_int8 int8,
  a_money money,
  an_enum ENUM_TEST,
  a_struct FULL_NAME,
  a_domain us_postal_code,
  a_varchar VARCHAR,
  a_varchar_10 VARCHAR(10),
  a_text text,
  a_char char,
  a_char_10 CHAR(10),
  a_bytea bytea,
  a_timestamp_3 TIMESTAMP(3),
  a_timestamp TIMESTAMP,
  a_timestamp_with_zone TIMESTAMP WITH TIME ZONE,
  a_date date,
  a_time TIME,
  an_interval INTERVAL,
  a_boolean BOOLEAN,
  a_cidr cidr,
  an_inet inet,
  a_macaddr macaddr,
  a_bit bit,
  a_bit_40 bit(40),
  a_bit_varying bit VARYING,
  a_tsvector tsvector,
  a_tsquery tsquery,
  an_uuid uuid,
  an_xml xml,
  a_json json
);

select * from types_test;
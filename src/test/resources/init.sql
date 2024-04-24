CREATE SCHEMA IF NOT EXISTS `newro-factory-db`;
SET SCHEMA `newro-factory-db`;

CREATE USER IF NOT EXISTS "testnewro" PASSWORD 'T4st3r!';
GRANT ALL PRIVILEGES ON SCHEMA `newro-factory-db` TO "testnewro";

drop database springboot_tutorial_db_prod;
drop user springboot_tutorial_prod;
create user springboot_tutorial_prod with password 'password';
create database springboot_tutorial_db_prod with template=template0 owner springboot_tutorial_prod;
\connect springboot_tutorial_db_prod;
alter default privileges grant all on tables to springboot_tutorial_prod;
alter default privileges grant all on sequences to springboot_tutorial_prod;
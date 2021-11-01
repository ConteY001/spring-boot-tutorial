drop database springboot_tutorial_db;
drop user springboot_tutorial;
create user springboot_tutorial with password 'password';
create database springboot_tutorial_db with template=template0 owner springboot_tutorial;
\connect springboot_tutorial_db;
alter default privileges grant all on tables to springboot_tutorial;
alter default privileges grant all on sequences to springboot_tutorial;
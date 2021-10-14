drop database springboot_tutorial_db_qa;
drop user springboot_tutorial_qa;
create user springboot_tutorial_qa with password 'password';
create database springboot_tutorial_db_qa with template=template0 owner springboot_tutorial_qa;
\connect springboot_tutorial_db_qa;
alter default privileges grant all on tables to springboot_tutorial_qa;
alter default privileges grant all on sequences to springboot_tutorial_qa;
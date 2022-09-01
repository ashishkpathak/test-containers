DROP TABLE IF EXISTS artist;
CREATE TABLE artist(id serial primary key, first_name VARCHAR(255), last_name VARCHAR(255));
insert into artist (first_name, last_name) values ('Vincent', 'Van gogh');
insert into artist (first_name, last_name) values ('Raja', 'Ravi Verma');
insert into artist (first_name, last_name) values ('Johannes', 'Vermeer');
insert into artist (first_name, last_name) values ('Amrita', 'Sher-Gil');
insert into artist (first_name, last_name) values ('Frida', 'Kahlo');
insert into artist (first_name, last_name) values ('Hokusai', 'Katsushika');

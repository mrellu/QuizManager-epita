create table QUIZ(id bigint auto_increment, name varchar(255) NOT NULL UNIQUE);
INSERT INTO  QUIZ ( name) VALUES ('JAVA');

select ID, NAME from QUIZ


insert into QUIZ (name) values ('Test Quiz')

UPDATE QUIZ SET NAME='Quiz Test 2' WHERE ID = 1;
DELETE QUIZ  WHERE ID = 1;



create table ANSWER(ID INT,ANSid bigint auto_increment, ANS varchar(255),OPTA varchar(255),OPTB varchar(255),OPTC varchar(255),OPTD varchar(255));


create table QUESTION (ID INT,QUESID bigint auto_increment, QUESTION varchar(255) NOT NULL UNIQUE,TOPIC varchar(255),DIFF int);



create table USER (ID INT auto_increment,USERNAME varchar(255)   NOT NULL UNIQUE, PASS varchar(255));
insert into user(USERNAME,PASS) VALUES ('HARISH','H@rish1991')

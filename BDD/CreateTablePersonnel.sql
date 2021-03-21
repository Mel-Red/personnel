DROP DATABASE IF EXISTS personnel;
CREATE DATABASE personnel;
USE personnel;

CREATE TABLE ligue
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nom varchar(50)
)
ENGINE=INNODB;

CREATE TABLE employe
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nom varchar(30),
	prenom varchar(30),
	password varchar(50),
	mail varchar(70),
	dateArrivee Date,
	dateDepart Date, 
	ligue_id int,
	is_admin tinyint default 2
)
ENGINE=INNODB;
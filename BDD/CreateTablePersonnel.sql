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
	ligue_id int NOT NULL,
	nom varchar(30),
	prenom varchar(30),
	mail varchar(70),
	mdp varchar(50),
	date_arrivee Date NOT NULL,
	date_depart Date, 
	is_admin boolean
)
ENGINE=INNODB;


CREATE TABLE ligue_admin
(
	ligue_id int NOT NULL,
	employe_id int NOT NULL
)
ENGINE=INNODB;
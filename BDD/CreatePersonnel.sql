DROP DATABASE IF EXISTS personnel;
CREATE DATABASE personnel;
USE personnel;

CREATE TABLE ligue
(
	idL char(6) NOT NULL,
	nomL varchar(25) NOT NULL
)
ENGINE=INNODB;

CREATE TABLE employe
(
	idE char(6) NOT NULL,
	idL char(6) NOT NULL,
	nomE varchar(15) NOT NULL,
	prenomE varchar(50) NOT NULL,
	mailE varchar(50),
	DateA Date NOT NULL, 
	DateD Date,
	MdpE varchar(50),
	isAdmin boolean
)
ENGINE=INNODB;

CREATE TABLE administrateur
(
	idE char(6) NOT NULL,
	idL char(6) NOT NULL
)
ENGINE=INNODB;


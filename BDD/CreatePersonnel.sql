DROP DATABASE personnel;
CREATE DATABASE personnel;
USE personnel;

CREATE TABLE employe1
(
	idE int(6),
	nomE varchar(15),
	prenomE varchar(15),
	mailE varchar(50),
	DateA Date, 
	DateD Date,
	MdpE varchar(50)
)
ENGINE=INNODB;

CREATE TABLE employe2
(
	idE int(6),
	nomE varchar(15),
	prenomE varchar(15),
	mailE varchar(50),
	DateA Date, 
	DateD Date,
	MdpE varchar(50),
	idLigue int(6)
)

CREATE TABLE ligue
(
	idL int(6),
	nomL varchar(25),
	idAdmin int(6)
)
ENGINE=INNODB;
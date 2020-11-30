DROP DATABASE personnel;
CREATE DATABASE personnel;
USE personnel;

CREATE TABLE employe
(
	idE int(6),
	nomE varchar(15),
	prenomE varchar(15),
	mailE varchar(50),
	DateA Date, 
	DateD Date,
	MdpE varchar(50),
	EstAdmin boolean,
	EstRoot boolean,
	idLigue int(6)
)
ENGINE=INNODB;

CREATE TABLE ligue
(
	idL int(6),
	nomL varchar(25)
)
ENGINE=INNODB;
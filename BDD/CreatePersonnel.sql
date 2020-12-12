DROP DATABASE personnel;
CREATE DATABASE personnel;
USE personnel;

CREATE TABLE employe
(
	idE int(6) NOT NULL,
	nomE varchar(15) NOT NULL,
	prenomE varchar(15) NOT NULL,
	mailE varchar(50),
	DateA Date NOT NULL, 
	DateD Date,
	MdpE varchar(50)
)
ENGINE=INNODB;

CREATE TABLE employeN
(
	idEmployee int(6) NOT NULL,
	isAdmin boolean
)
ENGINE=INNODB;

CREATE TABLE Admin
(
	idEmployee int (6) NOT NULL,
	idLigue int(6) NOT NULL
)
ENGINE=INNODB; 

CREATE TABLE ligue
(
	idL int(6) NOT NULL	
	nomL varchar(25) NOT NULL,
	idAdmin int(6)
)
ENGINE=INNODB;


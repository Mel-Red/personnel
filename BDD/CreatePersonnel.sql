DROP DATABASE IF EXIST personnel;
CREATE DATABASE personnel;

USE personnel;

CREATE TABLE employe
(
	code_e int(6);
	nom_e varchar(15);
	prenom_e varchar(15);
	mail_e varchar(50);
	ligue_e int(6);
	Date_arrivée_e Date; 
	Mdp_e varchar(50);
)
ENGINE=INNODB;

CREATE TABLE ligue
(
	code_ligue int(6);
	nom_ligue varchar(25);
	code_admin int(6);
	nom_admin varchar (15);
	prenom_admin varchar(15);
)
ENGINE=INNODB;


CREATE TABLE admin_ligue
(
	code_emp int(6);
	code_admin int(6);
	code_ligue int(6);
	mdp_admin varchar(50);
)
ENGINE=INNODB;
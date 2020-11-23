DROP DATABASE IF EXIST personnel;
CREATE DATABASE personnel;

USE personnel;

CREATE TABLE employe
(
	idE int(6);
	nom_e varchar(15);
	prenom_e varchar(15);
	mail_e varchar(50);
	Date_arrivee_e Date; 
	Date_depart Date;
	Mdp_e varchar(50);
	EstAdmin boolean;
	EstRoot boolean;
)
ENGINE=INNODB;

CREATE TABLE ligue
(
	idL int(6);
	nom_ligue varchar(25);
)
ENGINE=INNODB;
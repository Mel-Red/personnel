ALTER TABLE employe
	ADD CONSTRAINT PK_employe PRIMARY KEY (code_e);

ALTER TABLE ligue
	ADD CONSTRAINT PK_ligue PRIMARY KEY (code_ligue);

ALTER TABLE admin_ligue
	ADD CONSTRAINT PK_admin_ligue PRIMARY KEY (code_admin);
	ADD CONSTRAINT FK1_admin_ligue FOREIGN KEY (code_e) REFERENCES employe(code_e),
	ADD CONSTRAINT FK2_admin_ligue FOREIGN KEY (code_ligue) REFERENCES ligue(code_ligue);


/*salut toi ca va?
j'aime les coquelicots.*/


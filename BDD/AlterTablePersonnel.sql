ALTER TABLE employe
	ADD CONSTRAINT FK_Employe_Ligue FOREIGN KEY(ligue_id) REFERENCES ligue(id);

ALTER TABLE ligue_admin
	ADD CONSTRAINT PK_Ligue_Admin PRIMARY KEY(ligue_id, employe_id),
	ADD CONSTRAINT FK_LA_Ligue FOREIGN KEY(ligue_id) REFERENCES ligue(id),
	ADD CONSTRAINT FK_LA_Admin FOREIGN KEY(employe_id) REFERENCES employe(id);

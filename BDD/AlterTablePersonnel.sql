ALTER TABLE employe
	ADD CONSTRAINT FK_Employe_Ligue FOREIGN KEY(ligue_id) REFERENCES ligue(id);

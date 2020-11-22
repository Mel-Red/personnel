package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import personnel.*;

class testLigue 
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void createLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}
	
	@Test
	void setLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		ligue.setNom("New L1");
		assertEquals(ligue.getNom(), "New L1");
	}
	
	@Test
	void removeLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("ToBeDeleted");
		gestionPersonnel.remove(ligue); 
		assertEquals(gestionPersonnel.hasLigue(ligue), false);
	}
	
	@Test
	void compareLigue() throws SauvegardeImpossible
	{
		Ligue ligue1 = gestionPersonnel.addLigue("L1");
		Ligue ligue2 = gestionPersonnel.addLigue("L1");
		assertEquals(ligue1.compareTo(ligue2), 0);
	}

	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty"); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	@Test
	void removeEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		ligue.remove(employe);
		assertEquals(ligue.hasEmploye(employe), false);
	}
	
	@Test
	void setEmployeName() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		employe.setNom("New Name");
		assertEquals(employe.getNom(), "New Name");
	}
	
	@Test
	void setEmployeFName() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		employe.setPrenom("New LName");
		assertEquals(employe.getPrenom(), "New LName");
	}
	
	@Test
	void setEmployeMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		employe.setMail("newmail@gmail.com");
		assertEquals(employe.getMail(), "newmail@gmail.com");
	}
	
	@Test
	void setDateArrivee() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		try {
			employe.setDateArrivee("2020-10-20");
		} catch (ImpossibleDeChangerDate e) {
			e.printStackTrace();
		}
		assertEquals(employe.getDateArrivee(), "2020-10-20");
	}
	
	@Test
	void setDateDepart() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		try {
			employe.setDateDepart("2021-10-20");
		} catch (ImpossibleDeChangerDate e) {
			e.printStackTrace();
		}
		assertEquals(employe.getDateDepart(), "2021-10-20");
	}
	
	@Test
	void setEmployePassword() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		employe.setPassword("NewPassword");
		assertEquals(employe.checkPassword("NewPassword"), true);
	}
	
	@Test
	void setAdministrateur() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe administrateur = ligue.addEmploye("Admin", "Admin", "admin@gmail.com", "hello");
		ligue.setAdministrateur(administrateur);
		assertEquals(ligue.getAdministrateur(), administrateur);
	}
	
	@Test
	void testEstAdmin() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello");
		assertEquals(employe.estAdmin(ligue), false);
		ligue.setAdministrateur(employe);
		assertEquals(employe.estAdmin(ligue), true);
		Ligue ligue2 = gestionPersonnel.addLigue("L2");
		Employe employe2 = ligue2.addEmploye("DoDo", "HoHo", "DoDo.hoho@gmail.com", "hello");
		assertEquals(employe2.estAdmin(ligue), false);
	}
}

package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
	void addEmploye() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", null,null); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	@Test
	void removeEmploye() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		ligue.remove(employe);
		assertEquals(ligue.hasEmploye(employe), false);
	}
	
	@Test
	void setEmployeName() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		employe.setNom("New Name");
		assertEquals(employe.getNom(), "New Name");
	}
	
	@Test
	void setEmployeFName() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		employe.setPrenom("New LName");
		assertEquals(employe.getPrenom(), "New LName");
	}
	
	@Test
	void setEmployeMail() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		employe.setMail("newmail@gmail.com");
		assertEquals(employe.getMail(), "newmail@gmail.com");
	}
	
	@Test
	void setDateArrivee() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		try {
			employe.setDateArrivee(LocalDate.parse("2020-10-20"));
		} catch (ImpossibleDeChangerDate e) {
			e.printStackTrace();
		}
		assertEquals(employe.getDateArrivee(), "2020-10-20");
	}
	
	@Test
	void setDateDepart() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		try {
			employe.setDateDepart(LocalDate.parse("2021-10-20"));
		} catch (ImpossibleDeChangerDate e) {
			e.printStackTrace();
		}
		assertEquals(employe.getDateDepart(), "2021-10-20");
	}
	
	@Test
	void setEmployePassword() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		employe.setPassword("NewPassword");
		assertEquals(employe.checkPassword("NewPassword"), true);
	}
	
	@Test
	void setAdministrateur() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe administrateur = ligue.addEmploye("Admin", "Admin", "admin@gmail.com", "hello", null, null);
		ligue.setAdministrateur(administrateur);
		assertEquals(ligue.getAdministrateur(), administrateur);
	}
	
	@Test
	void testEstAdmin() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		Ligue ligue = gestionPersonnel.addLigue("L1");
		Employe employe = ligue.addEmploye("ToTo", "HoHo", "toto.hoho@gmail.com", "hello", null, null);
		assertEquals(employe.estAdmin(ligue), false);
		ligue.setAdministrateur(employe);
		assertEquals(employe.estAdmin(ligue), true);
		Ligue ligue2 = gestionPersonnel.addLigue("L2");
		Employe employe2 = ligue2.addEmploye("DoDo", "HoHo", "DoDo.hoho@gmail.com", "hello", null, null);
		assertEquals(employe2.estAdmin(ligue), false);
	}
}

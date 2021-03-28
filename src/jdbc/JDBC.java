package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(CredentialsExample.getDriverClassName());
			connection = DriverManager.getConnection(CredentialsExample.getUrl(), CredentialsExample.getUser(), CredentialsExample.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = null;
		try {
			gestionPersonnel = new GestionPersonnel();
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next()) {
				Ligue ligue = gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
				String requete2 = "select * from employe where ligue_id=" + ligue.getId();
				Statement instruction2 = connection.createStatement();
				ResultSet employes = instruction2.executeQuery(requete2);
				while (employes.next())
					ligue.addEmploye(employes.getInt(1), employes.getString(2), employes.getString(3), employes.getString(5), employes.getString(4), LocalDate.parse(employes.getDate(6).toString()), LocalDate.parse(employes.getDate(7).toString()));
			}
		} catch (SauvegardeImpossible e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (nom) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	@Override
	public int update(Ligue ligue) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("update ligue set nom = ? where id = ?");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
			return -1;
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	@Override
	public int delete(Ligue ligue) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("delete from ligue where id = ?");
			instruction.setInt(1, ligue.getId());
			instruction.executeUpdate();
			return -1;
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	@Override
	public int insertEmploye(Employe employe) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into employe (nom, prenom, password, mail, dateArrivee, dateDepart, ligue_id) values(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setString(4, employe.getMail());
			instruction.setString(5, employe.getDateArrivee().toString());
			instruction.setString(6, employe.getDateDepart().toString());
			instruction.setInt(7, employe.getLigue().getId());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	@Override
	public int updateEmploye(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("update employe set nom = ?, prenom = ?, password = ?, mail = ?, dateArrivee = ?, dateDepart = ? where id = ?");
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setString(4, employe.getMail());
			instruction.setString(5, employe.getDateArrivee().toString());
			instruction.setString(6, employe.getDateDepart().toString());
			instruction.setInt(7, employe.getId());
			instruction.executeUpdate();
			return -1;
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	@Override
	public int deleteEmploye(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("delete from employe where id = ?");
			instruction.setInt(1, employe.getId());
			instruction.executeUpdate();
			return -1;
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	
	@Override
	public int changeAdmin(Employe employe) throws SauvegardeImpossible
	{
		try
		{
			String requete = "select id from employe where role = 1 and ligue_id = " + employe.getLigue().getId();
			Statement instruction = connection.createStatement();
			ResultSet admins = instruction.executeQuery(requete);
			while (admins.next()) {
				PreparedStatement admin;
				admin = connection.prepareStatement("update employe set role = 2 where id = ?");
				admin.setInt(1, admins.getInt(1));
				admin.executeUpdate();
			}
			PreparedStatement admin;
			admin = connection.prepareStatement("update employe set role = 1 where id = ?");
			admin.setInt(1, employe.getId());
			admin.executeUpdate();
			return -1;
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
}

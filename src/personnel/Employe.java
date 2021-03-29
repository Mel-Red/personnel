package personnel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Employé d'une ligue hébergée par la M2L. Certains peuvent 
 * être administrateurs des employés de leur ligue.
 * Un seul employé, rattaché à aucune ligue, est le root.
 * Il est impossible d'instancier directement un employé, 
 * il faut passer la méthode {@link Ligue#addEmploye addEmploye}.
 */

public class Employe implements Serializable, Comparable<Employe>
{
	private static final long serialVersionUID = 4795721718037994734L;
	private String nom, prenom, password, mail;
	private LocalDate dateArrivee, dateDepart;
	private Ligue ligue;
	private GestionPersonnel gestionPersonnel;
	private int id;
	
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password, LocalDate dateArrivee, LocalDate dateDepart) throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.ligue = ligue;
		if (dateDepart != null && dateArrivee != null)
			try
		{
		boolean isAfter = dateArrivee.isAfter(dateDepart);
		if (isAfter)
			throw new ImpossibleDeChangerDate();
		else
			this.id = gestionPersonnel.insertEmploye(this);
		}
		catch (DateTimeParseException e) {
			System.out.println("Invalid date");
		}
	}
	
	Employe(GestionPersonnel gestionPersonnel, Ligue ligue, String nom, String prenom, String mail, String password, LocalDate dateArrivee, LocalDate dateDepart, int id)
	{
		this.gestionPersonnel = gestionPersonnel;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.mail = mail;
		this.dateArrivee = dateArrivee;
		this.dateDepart = dateDepart;
		this.ligue = ligue;
		this.id = id;
	}
	
	
	/**
	 * Retourne vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @return vrai ssi l'employé est administrateur de la ligue 
	 * passée en paramètre.
	 * @param ligue la ligue pour laquelle on souhaite vérifier si this 
	 * est l'admininstrateur.
	 */
	
	public boolean estAdmin(Ligue ligue)
	{
		return ligue.getAdministrateur() == this;
	}
	
	/**
	 * Retourne vrai ssi l'employé est le root.
	 * @return vrai ssi l'employé est le root.
	 * @throws ImpossibleDeChangerDate 
	 */
	
	public boolean estRoot() throws ImpossibleDeChangerDate
	{
		GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		if (gestionPersonnel != null) {
			return gestionPersonnel.getRoot() == this;
		}
		return false;
	}
	
	/**
	 * Retourne le nom de l'employé.
	 * @return le nom de l'employé. 
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Change le nom de l'employé.
	 * @param nom le nouveau nom.
	 * @throws SauvegardeImpossible 
	 */
	
	public void setNom(String nom) throws SauvegardeImpossible
	{
		this.nom = nom;
		this.id = gestionPersonnel.updateEmploye(this);
	}

	/**
	 * Retourne le prénom de l'employé.
	 * @return le prénom de l'employé.
	 */
	
	public String getPrenom()
	{
		return prenom;
	}
	
	/**
	 * Change le prénom de l'employé.
	 * @param prenom le nouveau prénom de l'employé. 
	 * @throws SauvegardeImpossible 
	 */

	public void setPrenom(String prenom) throws SauvegardeImpossible
	{
		this.prenom = prenom;
		this.id = gestionPersonnel.updateEmploye(this);
	}

	/**
	 * Retourne le mail de l'employé.
	 * @return le mail de l'employé.
	 */
	
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Change le mail de l'employé.
	 * @param mail le nouveau mail de l'employé.
	 * @throws SauvegardeImpossible 
	 */

	public void setMail(String mail) throws SauvegardeImpossible
	{
		this.mail = mail;
		this.id = gestionPersonnel.updateEmploye(this);
	}
	
	/**
	 * Retourne la date d'arrivée de l'employé.
	 * @return la date d'arrivée de l'employé.
	 */
	
	public LocalDate getDateArrivee()
	{
		return dateArrivee;
	}
	
	/**
	 * Change la date d'arrivée de l'employé.
	 * @return la date d'arrivée de l'employé.
	 * @throws SauvegardeImpossible 
	 */

	public void setDateArrivee(String dateArrivee) throws ImpossibleDeChangerDate, SauvegardeImpossible
	{
		try
		{
			dateArrivee = dateArrivee.replaceAll("/", "-");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT);
			LocalDate temp = LocalDate.parse(dateArrivee, formatter);
			if(dateDepart == null)
			{
				this.dateArrivee = temp;
				this.id = gestionPersonnel.updateEmploye(this);
			}
			else
			{
				boolean isAfter = temp.isAfter(dateDepart);
				 if (isAfter)
					throw new ImpossibleDeChangerDate();
				else
					this.dateArrivee = temp;
				 	this.id = gestionPersonnel.updateEmploye(this);
			}
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date");
		}
	}
	
	/**
	 * Retourne la date de départ de l'employé.
	 * @return la date de départ de l'employé.
	 */
	
	public LocalDate getDateDepart()
	{
		return dateDepart;
	}
	
	/**
	 * Change la date de départ de l'employé.
	 * @return la date de départ de l'employé.
	 * @throws SauvegardeImpossible 
	 */

	public void setDateDepart(String dateDepart) throws ImpossibleDeChangerDate, SauvegardeImpossible
	{
		try
		{
			dateDepart = dateDepart.replaceAll("/", "-");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT);
			LocalDate temp = LocalDate.parse(dateDepart, formatter);
			if(dateArrivee == null)
			{
				this.dateDepart = temp;
				this.id = gestionPersonnel.updateEmploye(this);
			}
			else 
			{
				boolean isBefore = temp.isBefore(dateArrivee);
				if (isBefore)
					throw new ImpossibleDeChangerDate();
				else
					this.dateDepart = temp;
					this.id = gestionPersonnel.updateEmploye(this);
			}
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date");
		}	
	}
	

	/**
	 * Retourne vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @return vrai ssi le password passé en paramètre est bien celui
	 * de l'employé.
	 * @param password le password auquel comparer celui de l'employé.
	 */
	
	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	/**
	 * Change le password de l'employé.
	 * @param password le nouveau password de l'employé. 
	 * @throws SauvegardeImpossible 
	 */
	
	public void setPassword(String password) throws SauvegardeImpossible
	{
		this.password= password;
		this.id = gestionPersonnel.updateEmploye(this);
	}
	
	public String getPassword() {
		return password;
	}

	/**
	 * Retourne la ligue à laquelle l'employé est affecté.
	 * @return la ligue à laquelle l'employé est affecté.
	 */
	
	public Ligue getLigue()
	{
		return ligue;
	}

	/**
	 * Supprime l'employé. Si celui-ci est un administrateur, le root
	 * récupère les droits d'administration sur sa ligue.
	 * @throws SauvegardeImpossible 
	 * @throws ImpossibleDeChangerDate 
	 */
	
	public void remove() throws SauvegardeImpossible, ImpossibleDeChangerDate
	{
		GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
		if (gestionPersonnel != null) {
			Employe root = gestionPersonnel.getRoot();
			if (this != root)
			{
				if (estAdmin(getLigue()))
					getLigue().setAdministrateur(root);
				ligue.remove(this);
				this.id = gestionPersonnel.deleteEmploye(this);
				
			}
			else
				throw new ImpossibleDeSupprimerRoot();
		}
	}

	@Override
	public int compareTo(Employe autre)
	{
		int cmp = getNom().compareTo(autre.getNom());
		if (cmp != 0)
			return cmp;
		return getPrenom().compareTo(autre.getPrenom());
	}
	
	@Override
	public String toString()
	{
		String res = nom + " " + prenom + " " + mail + " " + dateArrivee + " " + dateDepart + " (";
		try {
			if (estRoot())
				res += "super-utilisateur";
			else
				res += ligue.toString();
		} catch (ImpossibleDeChangerDate e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res + ")";
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}

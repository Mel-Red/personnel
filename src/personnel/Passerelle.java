package personnel;

public interface Passerelle 
{
	public GestionPersonnel getGestionPersonnel() throws ImpossibleDeChangerDate;
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel)  throws SauvegardeImpossible;
	public int insert(Ligue ligue) throws SauvegardeImpossible;
	public int insertEmploye(Employe employe) throws SauvegardeImpossible;
	public void update(Ligue ligue) throws SauvegardeImpossible;
	public void updateEmploye(Employe employe) throws SauvegardeImpossible;
	public void changeAdmin(Employe employe) throws SauvegardeImpossible;
	public void deleteEmploye(Employe employe) throws SauvegardeImpossible;
	public void delete(Ligue ligue) throws SauvegardeImpossible;
}

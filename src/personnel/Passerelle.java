package personnel;

public interface Passerelle 
{
	public GestionPersonnel getGestionPersonnel() throws ImpossibleDeChangerDate;
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel)  throws SauvegardeImpossible;
	public int insert(Ligue ligue) throws SauvegardeImpossible;
	public int insertEmploye(Employe employe) throws SauvegardeImpossible;
	public int update(Ligue ligue) throws SauvegardeImpossible;
	public int updateEmploye(Employe employe) throws SauvegardeImpossible;
	public int changeAdmin(Employe employe) throws SauvegardeImpossible;
	public int deleteEmploye(Employe employe) throws SauvegardeImpossible;
	public int delete(Ligue ligue) throws SauvegardeImpossible;
}

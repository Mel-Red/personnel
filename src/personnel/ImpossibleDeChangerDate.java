package personnel;

public class ImpossibleDeChangerDate extends Exception 
{
	public ImpossibleDeChangerDate () 
	{
		System.out.println("La date de d�part pr�c�de la date d'arriv�e, veuillez recommencer");
	}
}

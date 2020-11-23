package personnel;

public class ImpossibleDeChangerDate extends Exception 
{
	public ImpossibleDeChangerDate () 
	{
		System.out.println("La date de départ précède la date d'arrivée, veuillez recommencer");
	}
}

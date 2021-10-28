package Automate;

import java.util.Arrays;
import java.util.List;

import Utils.Constans;
import Utils.ValidationException;

/**
 * Cette classe nous permet de construire les automate avec les oparation de base
 * @author Rivaldes Hi
 */

public class AutomateBase {

	private Automate Automate;

	
	
	
	public static Automate AutomateSimple(String Regex) throws ValidationException{
		
		if( (!((List<String>)Arrays.asList(Constans.APHABET)).contains(Regex)))
			throw new ValidationException("Le n'apartient pas a l'alphabet");
		
		Automate aut = new Automate(Constans.APHABET);
		
		Etat etat1= new Etat(1) , etat2= new Etat(2);
		aut.ajouterUnEtat(etat1);
		aut.ajouterUnEtat(etat2);
		aut.ajouterUneTransition(etat1,Regex, etat2);
		aut.setInitialState(etat1);
		aut.ajouterUnEtatFinal(etat2);
		
		return aut;
	}
	
	public static Automate AutomateEtoil(Automate automat) throws ValidationException{
		
		Automate aut = new Automate(Constans.APHABET);
		
		// ajoute tout le transistion de l'etat final au etat
		
		return aut;
	}
	
	
	
	
	
	
	public Automate getAutomate() {
		return Automate;
	}

	public void setAutomate(Automate automate) {
		Automate = automate;
	}
}

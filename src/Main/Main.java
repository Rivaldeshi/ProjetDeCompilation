package Main;

import java.util.ArrayList;

import Utils.Constans;
import Utils.ValidationException;
import Automate.Automate;
import Automate.Etat;
import Automate.Transitions;

public class Main {

	public static void main(String[] args) {
	
		System.out.println("sa va coder fort");
		
		testTransistion();

	}
	
	
	public static void testTransistion(){
		
		ArrayList<Etat> etats = new ArrayList<Etat>(); 
		
		Etat etat1= new Etat(1) , etat2= new Etat(2), etat3= new Etat(3);
		etats.add(etat1);
		etats.add(etat2);
		etats.add(etat3);
	
		Automate aut = new Automate(Constans.APHABET_PAR_DEFAUT, etats);
		
		
		try {
			aut.ajouterUneTransition(etat1,"d",etat1);
			aut.ajouterUneTransition(etat1,Constans.APHABET_PAR_DEFAUT[0],etat2);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
		
		aut.ajouterUnEtat(new Etat(4));
		System.out.println(aut.getTransitionTable());
	}

}

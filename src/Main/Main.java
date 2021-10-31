package Main;

import Automate.*;
import Utils.Constans;
import Utils.ValidationException;

public class Main {

	public static void main(String[] args) {
	
		System.out.println("sa va coder tres fort");
		
		testTransistion();
		testAutomateSimple();

	}
	
	
	public static void testTransistion(){
		
		Etat etat1= new Etat(1) , etat2= new Etat(2), etat3= new Etat(4);
		
		Automate aut = new Automate(Constans.APHABET_PAR_DEFAUT);
		
		aut.ajouterUnEtat(etat1);
		aut.ajouterUnEtat(etat2);
		aut.ajouterUnEtat(etat3);
		
		try {
			aut.ajouterUneTransition(etat1,"c",etat2);
			aut.ajouterUneTransition(etat1,Constans.APHABET_PAR_DEFAUT[0],etat2);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	
		System.out.println(aut.getTransitionTable());
	}
	
	public static void testAutomateSimple(){
		
		
		try {
			Automate aut = AutomateBase.AutomateSimple("a");
			
			System.out.println(aut.getTransitionTable());
		} catch (ValidationException e) {
		
			e.printStackTrace();
		}
	}

}

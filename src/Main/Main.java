package Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import Automate.*;
import AutomateRegex.Verifications;
import Regex.RegexAnalyser;
import Utils.Constans;
import Utils.ValidationException;

public class Main {

	public static void main(String[] args) {

		System.out.println("sa va coder fort");
	 
		//testAutomate();
		 testVerif();

		// testTransistion();
		// testAutomateSimple();
		textRegex();
	}

	public static void textRegex() {
		String expressionReguliere = "+)(a+b)";
		char[] alphabet = { 'a', 'b', 'c' };
		RegexAnalyser reg = null;
		try {
			reg = new RegexAnalyser(expressionReguliere, alphabet);
			// RegexAnalyser.verifySiRegexApartientALaphabet(reg); (ne fonctione pas encor)
			RegexAnalyser.verifySiRegexEstBienFormer(reg);
			RegexAnalyser.verifySiRegexEstBienParentheser(reg);

		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void testTransistion() {

		Etat etat1 = new Etat(1), etat2 = new Etat(2), etat3 = new Etat(4);

		Automate aut = new Automate(Constans.APHABET_PAR_DEFAUT);

		aut.ajouterUnEtat(etat1);
		aut.ajouterUnEtat(etat2);
		aut.ajouterUnEtat(etat3);

		try {
			aut.ajouterUneTransition(etat1,"a",etat2);
			aut.ajouterUneTransition(etat1,Constans.APHABET_PAR_DEFAUT[0],etat2);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		System.out.println(aut.getTransitionTable());
		
	}
	
	public static void testAutomateSimple(){
		
		
		
		try {
			Automate automat1 =AutomateBase.AutomateSimple("a");
			Automate automat2 =AutomateBase.AutomateSimple("b");
			Automate aut = AutomateBase.AutomatePlus(automat1, automat2);
			
			System.out.println(aut.getTransitionTable());
			System.out.println(aut.getStates().size());
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
	}
	
	// test expresion regulierer (a+b)*abb;
	public static void testAutomate(){
		try {
			Automate aut = conca(conca(conca(etoil(ou( sim("a"),sim("b"))),sim("a")),sim("b")),sim("b"));
				
			List<Etat> detas= new ArrayList<Etat>();
			detas.add(aut.getInitialState());
			Transformation.epsilonFermeture(aut,detas,Constans.EPSILON);
			Transformation.Determiniser(aut);
			
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testVerif(){
		try {
			//Automate aut = conca(conca(conca(etoil(ou( sim("a"),sim("b"))),sim("a")),sim("b")),sim("b"));
			Automate aut =etoil( sim("a") );
		System.out.println(aut.getTransitionTable());
			Verifications.ApartientAutomate("aaaaa", aut);
			
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
   public static Automate ou(Automate a1,Automate a2 ) throws ValidationException{
	   return AutomateBase.AutomatePlus(a1,a2);
   }
   public static Automate conca(Automate a1,Automate a2 ) throws ValidationException{
	   return AutomateBase.AutomatePoint(a1,a2);
   }
   public static Automate sim(String s ) throws ValidationException{
	   return AutomateBase.AutomateSimple(s);
   }
   public static Automate etoil(Automate a1 ) throws ValidationException{
	   return AutomateBase.AutomateEtoil(a1);
   }


}

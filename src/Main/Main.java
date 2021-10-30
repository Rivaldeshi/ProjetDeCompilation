package Main;

import Automate.*;
import Regex.RegexAnalyser;
import Utils.Constans;
import Utils.ValidationException;

public class Main {

	public static void main(String[] args) {

		System.out.println("sa va coder fort");

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
			aut.ajouterUneTransition(etat1, "c", etat2);
			aut.ajouterUneTransition(etat1, Constans.APHABET_PAR_DEFAUT[0], etat2);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		System.out.println(aut.getTransitionTable());
	}

	public static void testAutomateSimple() {

		try {
			Automate aut = AutomateBase.AutomateSimple("a");

			System.out.println(aut.getTransitionTable());
		} catch (ValidationException e) {

			e.printStackTrace();
		}
	}

}

package Main;

import Regex.RegexAnalyser;
import Utils.Brouillon;
import Utils.ValidationException;
import View.MainView;

public class Main {

	public static void main(String[] args) {

		System.out.println("sa va coder fort");
		MainView main = new MainView();
		// MainView main = new MainView();
		// testAutomate();
		// Brouillon.testVerif();
		// Brouillon.testPostfix();
		// testTransistion();
		// testAutomateSimple();
		// textRegex();
		// Brouillon.testConcact();
		// Brouillon.test();

		//Brouillon.testAutomate();
	}

	public static void textRegex() {
		String expressionReguliere = "a.b(a+c)+d";
		String[] alphabet = { "a", "b", "c" };
		RegexAnalyser reg = null;
		try {
			reg = new RegexAnalyser(expressionReguliere, alphabet);
			RegexAnalyser.verifySiRegexApartientALaphabet(reg);
			// RegexAnalyser.verifySiRegexEstBienFormer(reg);
			// RegexAnalyser.verifySiRegexEstBienParentheser(reg);

		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}

	}

}

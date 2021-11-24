package Main;

import Regex.RegexAnalyser;
import Utils.ValidationException;
import View.MainView;

public class Main {

	public static void main(String[] args) {

		System.out.println("sa va coder fort");
		@SuppressWarnings("unused")
		MainView main = new MainView();
		// MainView main = new MainView();
		// testAutomate();
		// Brouillon.testVerhif();
		// Brouillon.testPostfix();
		// testTransistion();
		// testAutomateSimple();
		// textRegex();
		// Brouillon.testConcact();
		// Brouillon.test();

		// Brouillon.testTable();

		// Brouillon.testDessin();
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

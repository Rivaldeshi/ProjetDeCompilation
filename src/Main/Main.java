package Main;

import Regex.RegexAnalyser;
import Regex.TransformRegex;
import Utils.Brouillon;
import Utils.ValidationException;
import View.MainView;
public class Main {

	public static void main(String[] args) {

		System.out.println("sa va coder fort");
		MainView main = new MainView();
		// testAutomate();
		// Brouillon.testVerif();

		// Brouillon.testPostfix();
		// testTransistion();
		// testAutomateSimple();
		// textRegex();

		Brouillon.test();

	}

	public static void textRegex() {
		String expressionReguliere = "+)(a+b)";
		String[] alphabet = { "a", "b", "c" };
		RegexAnalyser reg = null;
		try {
			reg = new RegexAnalyser(expressionReguliere, alphabet);
			// RegexAnalyser.verifySiRegexApartientALaphabet(reg); (ne fonctione
			// pas encor)
			RegexAnalyser.verifySiRegexEstBienFormer(reg);
			RegexAnalyser.verifySiRegexEstBienParentheser(reg);

		} catch (ValidationException e) {
			System.out.println(e.getMessage());
		}

	}

}

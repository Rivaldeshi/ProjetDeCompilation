package Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import Automate.*;
import AutomateRegex.Verifications;
import Regex.RegexAnalyser;
import Utils.Brouillon;
import Utils.Constans;
import Utils.ValidationException;

public class Main {

	public static void main(String[] args) {


System.out.println("sa va coder tres fort");
		// testAutomate();
		// Brouillon.testVerif();

		// Brouillon.testPostfix();
	
		
		
		testTransistion();
		testAutomateSimple();
	 
		//testAutomate();
		Brouillon.testVerif();


		// testTransistion();
		// testAutomateSimple();
		// textRegex();

		Brouillon.test();

	}

	public static void textRegex() {
		String expressionReguliere = "+)(a+b)";
		char[] alphabet = { 'a', 'b', 'c' };
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

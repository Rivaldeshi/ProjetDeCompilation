package Regex;

import Utils.ValidationException;


/**
 * Cette classe nous permet d'effectuer les verification possible sur le regex
 * si elle est bien former ou elle appartient mm a l'alphabet
 * 
 * @author Rivaldes Hi
 */
public class RegexAnalyser {

	private String regex;
	private char[] alphabet;


	RegexAnalyser(String regex, char[] alphabet) throws ValidationException {
		this.setRegex(regex);

		if (alphabet.length == 0) {
			throw new ValidationException("l'alphabet est vide");
		}
		this.alphabet = alphabet;
	}

	/**
	 * cette fonction verifier si l'expression reguliere est bien parrantheser
	 * @return
	 */
	public static Boolean verifySiRegexEstBienParentheser() {
		return true;
	}
	
	
	/**
	 * cette fonction verifier si l'expression reguliere est bien former
	 * du genre elle ne commence pas par un operateur ou une parrenthese fermante et plus et pluss
	 * @return
	 */
	public static Boolean verifySiRegexEstBienFormer() {
		return true;
	}
	
	/**
	 * cette fonction verifier si l'expression reguliere est bien aparteient au Langage
	 * @return
	 */
	public static Boolean verifySiRegexApartientALaphabet() {
		return true;
	}
	
	

	public char[] getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(char[] alphabet) {
		this.alphabet = alphabet;
	}

	RegexAnalyser(String regex) {
		this.setRegex(regex);

	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	
}

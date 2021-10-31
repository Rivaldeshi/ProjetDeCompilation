package Regex;

import Utils.ValidationException;
import Utils.Constans;
import java.util.Arrays;


/**
 * Cette classe nous permet d'effectuer les verification possible sur le regex
 * si elle est bien former ou elle appartient mm a l'alphabet
 * 
 * @author Fredy
 */
public class RegexAnalyser {

	private String regex;
	private char[] alphabet;

	public RegexAnalyser(String regex, char[] alphabet) throws ValidationException {
		this.setRegex(regex);

		if (alphabet.length == 0) {
			throw new ValidationException("l'alphabet est vide");
		}
		this.alphabet = alphabet;
	}

	/**
	 * cette fonction verifier si l'expression reguliere est bien parrantheser
	 * 
	 * @return
	 */
	public static Boolean verifySiRegexEstBienParentheser(RegexAnalyser expression) throws ValidationException {
		int i = 0, j = 0;
		char[] symboles = expression.regex.toCharArray(); // je convertir l'objet string en un tableau de char
		for (char elt : symboles) {
			if (elt == '(')
				i++;
			if (elt == ')')
				j++;

		}

		if (i == j)
			return true;
		else {
			throw new ValidationException("expression reguliere mal parrentheser");
		}

	}

	/**
	 * cette fonction verifier si l'expression reguliere est bien former du genre
	 * elle ne commence pas par un operateur ou une parrenthese fermante et plus et
	 * pluss
	 * 
	 * @return
	 */
	public static Boolean verifySiRegexEstBienFormer(RegexAnalyser monRegex) throws ValidationException {
		char[] symboles = monRegex.regex.toCharArray();
		if (Arrays.asList(Constans.OPERATEUR_PAR_DEFAUT).contains(String.valueOf(symboles[0])) | symboles[0] == ')')
			throw new ValidationException("expression reguliere mal former ");
		// on va completer apres avoir bien manger

		else
			return true;
	}

	/**
	 * cette fonction verifier si l'expression reguliere est bien aparteient au
	 * Langage
	 * 
	 * @return
	 */
	public static Boolean verifySiRegexApartientALaphabet(RegexAnalyser monRegex) throws ValidationException {
		char[] symboles = monRegex.regex.toCharArray();
		for (char elt : symboles) {
			if (!(Arrays.asList(monRegex.alphabet).contains(String.valueOf(elt)))
					&& !(Arrays.asList(Constans.OPERATEUR_PAR_DEFAUT).contains(String.valueOf(elt))))
				throw new ValidationException("il ya des carracteres qui n'apartienent pas a l'alphabet ");
			// on retourne faux sil ya un carractere de l'expression reg qui n'appartient ni
			// a lalphabet , ni a un operateur
			// je vais gerer le cas des parrenthese apres

		}
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

package Regex;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Automate.Automate;
import Automate.AutomateOperation;
import Utils.Constans;
import Utils.ValidationException;

public class TransformRegex {

	public static String infixToPostfix(String exp) throws ValidationException {

		RegexAnalyser.verifySiRegexEstBienParentheser(new RegexAnalyser(exp,
				Constans.APHABET));

		String result = new String("");

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);
			if (Character.isLetterOrDigit(c))
				result += c;
			else if (c == '(')
				stack.push(c);

			else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					throw new ValidationException(
							"Expression est et mal former");
				}

			} else {
				while (!stack.isEmpty()
						&& prioritaire(c) < prioritaire(stack.peek())) {
					result += stack.pop();
				}
				stack.push(c);
			}

		}
		while (!stack.isEmpty()) {
			if (stack.peek() == '(')
				throw new ValidationException("Expression est et mal former");
			result += stack.pop();
		}

		return result;
	}

	public static int prioritaire(char op) {

		if (op == '+') {
			return 1;
		}
		if (op == '.') {
			return 2;
		}
		if (op == '*') {
			return 3;
		} else
			return -1;
	}

	public static Automate evaluateRegex(String Regex)
			throws ValidationException {

		if (Regex == null || Regex.equals("")) {
			throw new ValidationException("l'espression reguliere est vide");
		}
		Regex = metreLesoperteur(Regex);

		String exp = infixToPostfix(Regex);

		Stack<Automate> stack = new Stack<Automate>();
		List<String> alphabet = Arrays.asList(Constans.APHABET);

		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (alphabet.contains(c + ""))
				stack.push(sim(c + ""));

			else if (c == '*') {
				Automate aut;
				try {
					aut = stack.pop();
				} catch (Exception e) {
					throw new ValidationException(
							"L'Etoil ne peut etre en debut d'un expression reguliere");
				}

				stack.push(etoil(aut));
			} else {
				Automate b;
				Automate a;

				try {
					b = stack.pop();
					a = stack.pop();
				} catch (Exception e) {
					throw new ValidationException(
							"L'expression reguliere la mon amis est mal former");
				}
				switch (c) {
				case '+':
					stack.push(ou(a, b));
					break;
				case '.':
					stack.push(conca(a, b));
					break;
				}
			}
		}
		return stack.pop();
	}

	public static String metreLesoperteur(String regEx) {
		String formeAvecOperateur = "";

		for (int i = 0; i < regEx.length(); i++) {
			if (regEx.charAt(i) == '(' || Character.isLetter(regEx.charAt(i))) {
				if (i - 1 >= 0) {
					if (regEx.charAt(i - 1) != '('
							&& regEx.charAt(i - 1) != '+')
						formeAvecOperateur += ".";
				}
			}
			formeAvecOperateur += regEx.charAt(i);
		}

		return formeAvecOperateur;
	}

	public static Automate ou(Automate a1, Automate a2)
			throws ValidationException {
		return AutomateOperation.AutomatePlus(a1, a2);
	}

	public static Automate conca(Automate a1, Automate a2)
			throws ValidationException {
		return AutomateOperation.AutomatePoint(a1, a2);
	}

	public static Automate sim(String s) throws ValidationException {
		return AutomateOperation.AutomateSimple(s);
	}

	public static Automate etoil(Automate a1) throws ValidationException {
		return AutomateOperation.AutomateEtoil(a1);
	}

}

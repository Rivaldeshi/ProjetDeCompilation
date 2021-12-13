package AutomateRegex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Regex.RegexAnalyser;
import Utils.Constans;
import Utils.ValidationException;
import Automate.Automate;
import Automate.Determinisation;
import Automate.Etat;

/**
 * Cette classe Synthetise jute les autre classe et verifie si un mot apartient
 * a un automate
 * 
 * @author Rivaldes Hi
 */

public class Verifications {

	private String Mot;
	private static String alphabet;
	public static String trace = "";

	/**
	 * Verifiction si un mot appartient a un automate
	 * 
	 * @param mot
	 * @param automate
	 * @return
	 * @throws ValidationException
	 */

	public static boolean ApartientAutomate(String mot, Automate automate,
			ArrayList<Etat> trace) throws ValidationException {

		if (!RegexAnalyser.verifierSimotApartinentAphabet(mot))
			throw new ValidationException("Le mot n'apprtient pas a l'alphabet");

		// je cree genere l'automate determiniser
		Automate autdeter = Determinisation.Determiniser(automate);

		// je recupere l'etat initiale
		Etat q = autdeter.getInitialState();
		trace.add(q);
		// sachant l'automate est determiniser je parcoure juse mon mot et le
		// recupere les transition posible
		// si la dermerinere Etat est final on est bon sinon sava
		for (int i = 0; i < mot.length(); i++) {
			q = autdeter.getTransitionTable()
					.getTransition(q, mot.charAt(i) + "").get(0);
			trace.add(q);
		}
		return q.isFinal();
	}

	public static boolean ApartientAutomateAFN(Automate aut, String mot,
			Etat etatInitiale, ArrayList<Etat> trace)
			throws ValidationException {

		trace.add(etatInitiale);
		if ("".equals(mot)) {

			List<Etat> l = aut.getTransitionTable().getTransition(etatInitiale,
					Constans.EPSILON);
		   Collections.reverse(l);
			if (l.size() != 0) {
				for (  Etat et : l) {
					if (et.isFinal()) {
						trace.add(et);
						return true;
					} else {
						ArrayList<Etat> tr = new ArrayList<Etat>();
						if (ApartientAutomateAFN(aut, mot, et, tr)) {
							trace.addAll(tr);
							return true;
						}
					}
				}
			}
			return aut.getFinalStates().contains(etatInitiale);
		}
		List<Etat> list = aut.getTransitionTable().getTransition(etatInitiale,
				mot.charAt(0) + "");

		if (!list.isEmpty()) {
			for (Etat iState : list) {
				ArrayList<Etat> tr = new ArrayList<Etat>();
				if (ApartientAutomateAFN(aut, mot.substring(1), iState, tr)) {
					trace.addAll(tr);
					return true;
				}
			}
		}
		list = aut.getTransitionTable().getTransition(etatInitiale,
				Constans.EPSILON);
		if (list != null && !list.isEmpty()) {
			for (Etat iState : list) {
				ArrayList<Etat> tr = new ArrayList<Etat>();
				if (ApartientAutomateAFN(aut, mot, iState, tr)) {
					trace.addAll(tr);
					return true;
				}
			}
		}
		return false;
	}

	public static boolean succesive(ArrayList<Etat> trace, Etat etat1,
			Etat etat2) {
		for (int i = 0; i < trace.size() - 1; i++) {
			if (trace.get(i).equals(etat1) && trace.get(i + 1).equals(etat2))
				return true;

		}
		return false;
	}

	public String getMot() {
		return Mot;
	}

	public void setMot(String mot) {
		Mot = mot;
	}

	public static String getAlphabet() {
		return alphabet;
	}

	public static void setAlphabet(String alphabet) {
		Verifications.alphabet = alphabet;
	}

}

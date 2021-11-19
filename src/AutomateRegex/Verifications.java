package AutomateRegex;
import Utils.ValidationException;
import Automate.Automate;
import Automate.Determinisation;
import Automate.Etat;


/**
 * Cette classe Synthetise jute les autre classe et verifie si un mot apartient a un automate
 * 
 * @author Rivaldes Hi
 */

public class Verifications {

	private String Mot;
	private static String alphabet;
    public static String trace="";

	/**
	 * Verifiction si un mot appartient a un automate
	 * @param  mot
	 * @param automate
	 * @return
	 * @throws ValidationException
	 */
	
	public static boolean ApartientAutomate(String mot, Automate automate)
			throws ValidationException {
		trace="";

		// je cree genere l'automate determiniser
		Automate autdeter = Determinisation.Determiniser(automate);
		
		// je recupere l'etat initiale
		Etat q= autdeter.getInitialState();
		trace+= " -> "+q;
		//sachant l'automate est determiniser je parcoure juse mon mot et le recupere les transition posible
		// si la dermerinere Etat est final on est bon sinon sava 
		for(int i=0;i<mot.length();i++){
			q = autdeter.getTransitionTable().getTransition(q,mot.charAt(i)+"").get(0);
			trace+= " -> "+q;
		}
		System.out.println(trace);
		return q.isFinal();
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

package AutomateRegex;

import java.util.Arrays;
import java.util.List;

import Utils.Constans;
import Utils.ValidationException;
import Automate.Automate;
import Automate.AutomateBase;
import Automate.Etat;
import Automate.Transformation;
import Automate.Transitions;

public class Verifications {

	private String Mot;
	private static String alphabet;

	public static boolean ApartientAutomate(String mot, Automate automate)
			throws ValidationException {

		List<String> alphabe = Arrays.asList(alphabet);

		if ((mot == null || mot.equals(""))) {
			if (automate.getFinalStates().contains(automate.getInitialState()))
				return true;
			else
				System.out.println("atend un peu");
			return false;
			// les transition etiqueter par epsilon de a au une etat finale
		}
		Automate autdeter = Transformation.Determiniser(automate);
		
		Transitions transition = autdeter.getTransitionTable();
		
		Etat q= autdeter.getInitialState();
		
		for(int i=0;i<mot.length();i++){
			q = autdeter.getTransitionTable().getTransition(q,mot.charAt(i)+"").get(0);
			
		}
		System.out.println(q);
		return q.isFinal();
	}

}

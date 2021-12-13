package Automate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

import Utils.ValidationException;

@SuppressWarnings("serial")
public class Transitions extends HashMap<Etat, HashMap<String, List<Etat>>> {

	String[] Alphabet;

	Transitions() {
		super();
	}

	public Transitions(String[] alphabet) {
		super();
		this.Alphabet = alphabet;
	}

	public Transitions(String[] alphabet, ArrayList<Etat> etats) {
		super();
		this.Alphabet = alphabet;
		for (Etat etat : etats) {
			this.AjouterEtat(etat);
		}
	}

	public void AjouterEtat(Etat etat) {
		HashMap<String, List<Etat>> entrer = new HashMap<String, List<Etat>>();
		for (String sysmbol : Alphabet) {
			entrer.put(sysmbol, new ArrayList<Etat>());
		}
		this.put(etat, entrer);
	}

	public void AjouterTransition(Etat state, String Etiquet, Etat suivant) throws ValidationException {

		List<String> valid = Arrays.asList(Alphabet);

		if (valid.contains(Etiquet)) {
			this.get(state).get(Etiquet).add(suivant);
		} else {
			throw new ValidationException(
					"L'etiquet \"" + Etiquet + "\" de la transition n'apartient pas a l'alphabet");
		}
	}

	public List<Etat> getTransition(Etat et, String Etiquet) throws ValidationException {

		if (!Character.isLetterOrDigit(Etiquet.charAt(0))) {
			throw new ValidationException("L'etiquet \"" + Etiquet + "\" n'est pas un carractere de l'alphabet");
		}
		return this.get(et).get(Etiquet) != null ? this.get(et).get(Etiquet) : new ArrayList<Etat>();

	}

}

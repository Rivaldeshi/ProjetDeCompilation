package Automate;

import java.util.HashSet;
import java.util.List;

/**
 * Cette classe nous permet de materialiser un Detat dans les affaire de
 * epsilonfermerture la
 * 
 * @author Rivaldes Hi
 */
public class Detats {

	List<Etat> List;
	Etat Etat;
	boolean estMarquer;

	Detats(List<Etat> list2, int etat) {
		this.List = list2;
		this.Etat = new Etat(etat);
	}

	public String toString() {

		return "{ " + this.Etat + " = " + List + " est marquer " + estMarquer + "  }";

	}

	@Override
	public boolean equals(Object o) {
		@SuppressWarnings("unchecked")
		List<String> l2 = (List<String>) o;
		if (List.size() != l2.size()) {
			return false;
		}
		
		return new HashSet<>(this.List).equals(new HashSet<>(l2));
	}

}

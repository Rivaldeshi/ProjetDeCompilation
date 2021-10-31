package Utils;

import java.util.ArrayList;
import java.util.List;

import Automate.Automate;
import Automate.AutomateOperation;
import Automate.Determinisation;
import Automate.Etat;
import AutomateRegex.Verifications;

public class Brouillon {
	
	

	public static void testTransistion() {

		Etat etat1 = new Etat(1), etat2 = new Etat(2), etat3 = new Etat(4);

		Automate aut = new Automate(Constans.APHABET_PAR_DEFAUT);

		aut.ajouterUnEtat(etat1);
		aut.ajouterUnEtat(etat2);
		aut.ajouterUnEtat(etat3);

		try {
			aut.ajouterUneTransition(etat1, "a", etat2);
			aut.ajouterUneTransition(etat1, Constans.APHABET_PAR_DEFAUT[0],
					etat2);
		} catch (ValidationException e) {
			e.printStackTrace();
		}

		System.out.println(aut.getTransitionTable());

	}

	public static void testAutomateSimple() {

		try {
			Automate automat1 = AutomateOperation.AutomateSimple("a");
			Automate automat2 = AutomateOperation.AutomateSimple("b");
			Automate aut = AutomateOperation.AutomatePlus(automat1, automat2);

			System.out.println(aut.getTransitionTable());
			System.out.println(aut.getStates().size());
		} catch (ValidationException e) {
			e.printStackTrace();
		}

	}

	// test expresion regulierer (a+b)*abb;
	public static void testAutomate() {
		try {
			Automate aut = conca(
					conca(conca(etoil(ou(sim("a"), sim("b"))), sim("a")),
							sim("b")), sim("b"));

			List<Etat> detas = new ArrayList<Etat>();
			detas.add(aut.getInitialState());
			Determinisation.epsilonFermeture(aut, detas);
			Determinisation.Determiniser(aut);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testVerif() {
		try {
		    Automate aut = conca(conca(conca(etoil(ou( sim("a"),sim("b"))),sim("a")),sim("b")),sim("b"));
		//	Automate aut = etoil(sim(""));

			Verifications.ApartientAutomate("aaaaabbbaabb", aut);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

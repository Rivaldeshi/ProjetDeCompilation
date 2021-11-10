package Automate;

import java.util.Arrays;
import java.util.List;

import Utils.Constans;
import Utils.ValidationException;

/**
 * Cette classe nous permet de construire les automate avec les oparation de
 * base tout ses fontion sont static
 * 
 * @author Rivaldes Hi
 */

public class AutomateOperation {

	private Automate Automate;

	/**
	 * Construction d'un automate avec un seul symmble
	 * 
	 * @param le symbole
	 * @return
	 * @throws ValidationException
	 */
	public static Automate AutomateSimple(String Symbole) throws ValidationException {

		// si le symbole n'apartien pas a l'aphabet erreure
		if ((!((List<String>) Arrays.asList(Constans.APHABET)).contains(Symbole)))
			throw new ValidationException("Le Symbole n'apartient " + Symbole + " pas a l'alphabet");

		// cree un automate vierge
		Automate aut = new Automate(Constans.APHABET);

		// cree deux etats ajoute 1 comme initial et l'autre comme final
		Etat etat1 = new Etat(1), etat2 = new Etat(2);
		aut.ajouterUnEtat(etat1);
		aut.ajouterUnEtatFinal(etat2);

		// ajoute un transition entre les 2
		aut.ajouterUneTransition(etat1, Symbole, etat2);
		aut.setInitialState(etat1);
		return aut;
	}

	/**
	 * hahaha c'est n'est pas facile Construction de Etoil d'un automate
	 * 
	 * @param automat
	 * @return Automate
	 * @throws ValidationException
	 */
	public static Automate AutomateEtoil(Automate automat) throws ValidationException {

		Automate aut = new Automate(Constans.APHABET);

		// je cree la nouvelle etats initail
		Etat newinitial = new Etat(aut.getStates().size());

		// je requpere l'accient etat initial por faire mes transition apres
		Etat oldinitial = automat.getInitialState();
		// je rend n'est plus initial
		oldinitial.setInitial(false);

		// j'ajoute le nouveau a l'automate en cour de construction
		aut.ajouterUnEtat(newinitial);
		aut.setInitialState(newinitial);

		// ajoute tout les etat de l'ancient au nouveau
		// puis transistion de l'etat final au l'ancient initial
		for (Etat et : automat.getStates()) {

			aut.ajouterUnEtat(et.SetState(aut.getStates().size()));
			if (et.isFinal()) {
				aut.ajouterUneTransition(et, Constans.EPSILON, oldinitial);
			}

		}
		// ajouter une epsilone transition de l'ancient iniatial au nouveau
		aut.ajouterUneTransition(newinitial, Constans.EPSILON, oldinitial);

		Etat finall = new Etat(aut.getStates().size());

		aut.ajouterUnEtatFinal(finall);
		aut.ajouterUneTransition(newinitial, Constans.EPSILON, finall);

		automat.getStates().get(automat.getStates().size() - 1).setFinal(false);
		aut.getFinalStates().remove(automat.getStates().get(automat.getStates().size() - 1));
		;

		aut.ajouterUneTransition(automat.getStates().get(automat.getStates().size() - 1), Constans.EPSILON, finall);

		// je remet les transistion que l'ancient automate avait
		for (Etat etat : automat.getStates()) {
			for (String Etiquet : Constans.APHABET) {
				for (Etat et1 : automat.getTransitionTable().getTransition(etat, Etiquet)) {
					aut.ajouterUneTransition(etat, Etiquet, et1);
				}
			}
		}
		return aut;
	}

	public static Automate AutomatePoint(Automate automat1, Automate automat2) throws ValidationException {

		Automate res = new Automate(Constans.APHABET);

		// j'ajoute les etat du premier automate
		for (Etat et : automat1.getStates()) {
			res.ajouterUnEtat(et.SetState(res.getStates().size()));
		}

		res.setInitialState(automat1.getInitialState());

		automat1.getFinalStates().get(0).setFinal(false);
		res.getFinalStates().remove(automat1.getFinalStates().get(0));

		automat2.getInitialState().setInitial(false);
		// j'ajoute les automate de etat du deuxieme automate
		for (Etat et : automat2.getStates()) {
			res.ajouterUnEtat(et.SetState(res.getStates().size()));
		}

		// je met le pont
		res.ajouterUneTransition(automat1.getFinalStates().get(0), Constans.EPSILON, automat2.getInitialState());

		for (Etat etat : automat1.getStates()) {
			for (String Etiquet : Constans.APHABET) {
				for (Etat et1 : automat1.getTransitionTable().getTransition(etat, Etiquet)) {
					res.ajouterUneTransition(etat, Etiquet, et1);
				}
			}
		}
		for (Etat etat : automat2.getStates()) {
			for (String Etiquet : Constans.APHABET) {
				for (Etat et1 : automat2.getTransitionTable().getTransition(etat, Etiquet)) {
					res.ajouterUneTransition(etat, Etiquet, et1);
				}
			}
		}

		return res;
	}

	public static Automate AutomatePlus(Automate automat1, Automate automat2) throws ValidationException {

		Automate res = new Automate(Constans.APHABET);

		Etat newinitial = new Etat(res.getStates().size());

		res.ajouterUnEtat(newinitial);

		res.setInitialState(newinitial);

		// j'ajoute les etat du premier automate
		for (Etat et : automat1.getStates()) {
			res.ajouterUnEtat(et.SetState(res.getStates().size()));
		}

		// j'ajoute les etat du premier automate
		for (Etat et : automat2.getStates()) {
			res.ajouterUnEtat(et.SetState(res.getStates().size()));
		}

		res.ajouterUneTransition(newinitial, Constans.EPSILON, automat1.getInitialState());
		automat1.getInitialState().setInitial(false);

		res.ajouterUneTransition(newinitial, Constans.EPSILON, automat2.getInitialState());
		automat2.getInitialState().setInitial(false);

		Etat finall = new Etat(res.getStates().size());
		res.ajouterUnEtatFinal(finall);

		res.ajouterUneTransition(automat1.getFinalStates().get(0), Constans.EPSILON, finall);
		automat1.getFinalStates().get(0).setFinal(false);

		res.ajouterUneTransition(automat2.getFinalStates().get(0), Constans.EPSILON, finall);
		automat2.getFinalStates().get(0).setFinal(false);

		for (Etat etat : automat1.getStates()) {
			for (String Etiquet : Constans.APHABET) {
				for (Etat et1 : automat1.getTransitionTable().getTransition(etat, Etiquet)) {
					res.ajouterUneTransition(etat, Etiquet, et1);
				}
			}
		}
		for (Etat etat : automat2.getStates()) {
			for (String Etiquet : Constans.APHABET) {
				for (Etat et1 : automat2.getTransitionTable().getTransition(etat, Etiquet)) {
					res.ajouterUneTransition(etat, Etiquet, et1);
				}
			}
		}

		return res;
	}

	public Automate getAutomate() {
		return Automate;
	}

	public void setAutomate(Automate automate) {
		Automate = automate;
	}
}

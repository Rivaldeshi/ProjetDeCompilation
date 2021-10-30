package Automate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Utils.Constans;
import Utils.ValidationException;

public class Transformation {

	public static Automate Determiniser(Automate automat)
			throws ValidationException {

		Automate autdeter = new Automate(Constans.APHABET);
		Queue<Detats> Detat = new ArrayDeque<Detats>();

		List<Detats> detats = new ArrayList<Detats>();

		List<Etat> detas = new ArrayList<Etat>();
		detas.add(automat.getInitialState());
		Detats Q0 = new Detats(epsilonFermeture(automat, detas,
				Constans.EPSILON), autdeter.getStates().size());

		autdeter.ajouterUnEtat(Q0.Etat);
		autdeter.setInitialState(Q0.Etat);
		Detat.add(Q0);

		while (!Detat.isEmpty()) {
			Detats deta = Detat.remove();
			deta.estMarquer = true;
			detats.add(deta);
			for (String Sym : Constans.APHABET) {
				if (!Sym.equals(Constans.EPSILON)) {
					List<Etat> transiter = Transiter(automat, deta.List, Sym);

					List<Etat> u = epsilonFermeture(automat, transiter,
							Constans.EPSILON);
					boolean contains = true;

					for (int i = 0; i < detats.size(); i++) {
						if (detats.get(i).equals(u)) {
							contains = false;
							autdeter.ajouterUneTransition(deta.Etat, Sym,
									detats.get(i).Etat);
						}
					}

					if (contains) {
						
						Detats Q = new Detats(u, autdeter.getStates().size());
						
						for(Etat et:u){
							if(et.isFinal()){
								Q.Etat.setIsFinal();
							}
						}
						
						autdeter.ajouterUnEtat(Q.Etat);
						autdeter.ajouterUneTransition(deta.Etat, Sym, Q.Etat);
						Detat.add(Q);
					}
				}
			}
		}
		
		System.out.println("autdetermiser = "+autdeter.getTransitionTable());
		return autdeter;
	}

	public static List<Etat> Transiter(Automate aut, List<Etat> etats,
			String Sysm) {
		List<Etat> etatss = new ArrayList<Etat>();
		for (Etat et : etats) {
			List<Etat> eta = aut.getTransitionTable().getTransition(et, Sysm);

			for (Etat i : eta) {
				etatss.add(i);
			}
		}
		return etatss;
	}

	public static boolean Equal(List<Etat> l1, List<Etat> l2) {
		if (l1.size() != l2.size()) {
			return false;
		}
		for (int i = 0; i < l1.size(); i++) {
			if (!l1.get(i).equals(l2.get(i)))
				return false;
		}
		return true;
	}

	public static List<Etat> epsilonFermeture(Automate aut, List<Etat> etats,
			String Etiquet) {
		ArrayList<Etat> etatEpsilonesque = new ArrayList<Etat>();
		for (Etat etat : etats) {
			etatEpsilonesque.add(etat);
			Stack<Etat> pile = new Stack<Etat>();
			List<Etat> transitiondirect = aut.getTransitionTable()
					.getTransition(etat, Etiquet);

			for (Etat et : transitiondirect) {
				etatEpsilonesque.add(et);
				pile.push(et);
			}

			while (!pile.empty()) {

				Etat t = pile.pop();
				List<Etat> transition = aut.getTransitionTable().getTransition(
						t, Etiquet);
				for (Etat et : transition) {
					if (!etatEpsilonesque.contains(et)) {
						etatEpsilonesque.add(et);
						pile.push(et);
					}
				}

			}
		}
		return etatEpsilonesque;
	}

}

// public static Automate Determiniser(Automate automat)
// throws ValidationException {
//
// Automate autdeter = new Automate(Constans.APHABET);
// List<Detats> Detat = new ArrayList<Detats>();
// List<Etat> detas = new ArrayList<Etat>();
// detas.add(automat.getInitialState());
// Detats Q0 = new Detats(epsilonFermeture(automat, detas,
// Constans.EPSILON), autdeter.getStates().size());
//
// autdeter.ajouterUnEtat(Q0.Etat);
// autdeter.setInitialState(Q0.Etat);
// Detat.add(Q0);
//
// for (ListIterator<Detats> detas1 = Detat.listIterator(); detas1
// .hasNext();) {
// Detats deta = detas1.next();
// deta.estMarquer = true;
//
// for (String Sym : Constans.APHABET) {
// List<Etat> transiter = Transiter(automat, deta.List, Sym);
// List<Etat> u = epsilonFermeture(automat, transiter,
// Constans.EPSILON);
// boolean contains = true;
//
// for (int i = 0; i < Detat.size(); i++) {
//
// if (Detat.get(i).equals(u)) {
// contains = false;
// autdeter.ajouterUneTransition(deta.Etat, Sym,
// Detat.get(i).Etat);
// break;
// }
// }
//
// if (contains) {
// Detats Q = new Detats(u, autdeter.getStates().size());
// autdeter.ajouterUnEtat(Q.Etat);
// detas1.add(Q);
//
// }
//
// }
//
// }
// for (Detats eta : Detat) {
//
// System.out.println(eta);
// }
//
// System.out.println(autdeter.getTransitionTable());
//
// return null;
// }
//
// }


package Automate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Utils.Constans;
import Utils.ValidationException;

/**
 * Cette classe de determiniser un automate pour pouvoire verifier apres si un
 * mot apartient a un automate facilement je vous avertir c'est n'est pas faciel
 * 
 * @author Rivaldes Hi
 */

public class Determinisation {

	/**
	 * cette fonction nous retourne juste l'epsilone fermeture d'une liste
	 * d'etat facilement comme sa
	 * 
	 * @param aut
	 *            automate en question
	 * @param etats
	 *            la liste de chose a espsilone fermeturer
	 * @throws ValidationException
	 */

	public static List<Etat> epsilonFermeture(Automate aut, List<Etat> etats)
			throws ValidationException {
		ArrayList<Etat> etatEpsilonesque = new ArrayList<Etat>();

		// c'est juste l'algorithme du coure faur=t lire page 78 on a juste mit
		// la boucle
		for (Etat etat : etats) {
            if(!etatEpsilonesque.contains(etat)){
			etatEpsilonesque.add(etat);
			Stack<Etat> pile = new Stack<Etat>();
			List<Etat> transitiondirect = aut.getTransitionTable()
					.getTransition(etat, Constans.EPSILON);

			for (Etat et : transitiondirect) {
				etatEpsilonesque.add(et);
				pile.push(et);
			}

			while (!pile.empty()) {

				Etat t = pile.pop();
				List<Etat> transition = aut.getTransitionTable().getTransition(
						t, Constans.EPSILON);
				for (Etat et : transition) {
					if (!etatEpsilonesque.contains(et)) {
						etatEpsilonesque.add(et);
						pile.push(et);
					}
				}

			}
		}}
		return etatEpsilonesque;
	}

	/**
	 * cette fonction nous retourne l'automate determimiser d'un automate
	 * 
	 * @param automat
	 *            automate en question
	 * 
	 */
	public static Automate Determiniser(Automate automat)
			throws ValidationException {

		// creation du nouveau automate
		Automate autdeter = new Automate(Constans.APHABET);

		// une fille temporaire de Detas ou on aurra les etat non marquer
		Queue<Detats> Detat = new ArrayDeque<Detats>();

		// liste de touts le Detats
		List<Detats> detats = new ArrayList<Detats>();

		// juste metre l'etat intial dans une liste pour pouvoire apeler notre
		// epsilon fermeture
		List<Etat> initials = new ArrayList<Etat>();
		initials.add(automat.getInitialState());

		// la premier Detat issue de qo
		Detats Q0 = new Detats(epsilonFermeture(automat, initials), autdeter
				.getStates().size());

		// on verifie si Q0 a un etat finale dans lui si oui elle devient aussi
		// finale
		for (Etat et : epsilonFermeture(automat, initials)) {
			if (et.isFinal()) {
				Q0.Etat.setIsFinal();
			}
		}

		// on ajoute l'etat comme initial a l'automate une fois
		autdeter.ajouterUnEtat(Q0.Etat);
		autdeter.setInitialState(Q0.Etat);

		// on ajoute dans notre file
		Detat.add(Q0);
		detats.add(Q0);

		// tanque il ya les Detats dans la lite des Detas Non marquer
		while (!Detat.isEmpty()) {

			// j'ajoute la Detat au etat marquer
			Detats deta = Detat.remove();
			deta.estMarquer = true;

			// je parcour l'aphabet sauf EPSILON pour trouver les nouveau Detas
			for (String Sym : Constans.APHABET) {
				if (!Sym.equals(Constans.EPSILON)) {

					// je chercher la transister du detats courant par raport al
					// la lettre couranter
					List<Etat> transiter = Transiter(automat, deta.List, Sym);

					// calclue epsilone fermature
					List<Etat> u = epsilonFermeture(automat, transiter);
					// verifie si la Detats trouver existe deja
					boolean contains = true;
					for (int i = 0; i < detats.size(); i++) {
						if (detats.get(i).equals(u)) {
							contains = false;
							// si oui on ajoute la transition
							autdeter.ajouterUneTransition(deta.Etat, Sym,
									detats.get(i).Etat);
						}
					}

					// si non on la cree et on ajoute a la file
					if (contains) {
						Detats Q = new Detats(u, autdeter.getStates().size());

						// on verifi si la Detats contient une etat finale si
						// oui l'etat cree sera final
						for (Etat et : u) {
							if (et.isFinal()) {
								Q.Etat.setIsFinal();
							}
						}
						// ajoute l'etat et la transition a l'automate

						Detat.add(Q);
						detats.add(Q);
						autdeter.ajouterUnEtat(Q.Etat);
						autdeter.ajouterUneTransition(deta.Etat, Sym, Q.Etat);

						if (detats.size() > 50) {
							for(Detats det : detats){
								System.out.println(det);
							}
							throw new ValidationException("Determinisation a echouer je ne sais pas pourquoir ");
						}
					}
				}
			}
		}
		System.out.println("autdetermiser = " + autdeter.getTransitionTable());
		return autdeter;
	}

	public static List<Etat> Transiter(Automate aut, List<Etat> etats,
			String Sysm) throws ValidationException {
		List<Etat> etatss = new ArrayList<Etat>();
		for (Etat et : etats) {
			List<Etat> eta = aut.getTransitionTable().getTransition(et, Sysm);

			for (Etat i : eta) {
				etatss.add(i);
			}
		}
		return etatss;
	}

}

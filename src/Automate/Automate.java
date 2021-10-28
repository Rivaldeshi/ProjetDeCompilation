package Automate;

import java.util.ArrayList;

import Utils.Constans;
import Utils.ValidationException;
/**
 * Cette classe nous permet d'effectuer les tache de base d'un automate
 * 
 * @author Rivaldes Hi
 */
public class Automate {

	private ArrayList<Etat> states;
	private Etat initialState;

	private ArrayList<Etat> finalStates;

	private String[] alphabet;

	private Transitions transitionTable;

	public Automate() {
		finalStates = new ArrayList<Etat>();
		states = new ArrayList<Etat>();
		alphabet = Constans.APHABET_PAR_DEFAUT;
		transitionTable = new Transitions(alphabet);
	}

	public Automate(String[] Alphabet) {
		finalStates = new ArrayList<Etat>();
		states = new ArrayList<Etat>();
		alphabet = Alphabet;
		transitionTable = new Transitions(alphabet);
	}

	public Automate(String[] Alphabet, ArrayList<Etat> states) {
		finalStates = new ArrayList<Etat>();
		this.states = states;
		alphabet = Alphabet;
		transitionTable = new Transitions(alphabet, states);
	}

	public void ajouterUnEtat(Etat state) {
		this.states.add(state);
		transitionTable.AjouterEtat(state);
	}
	
	public void ajouterUnEtatFinal(Etat state) {
		state.setIsFinal();
		this.finalStates.add(state);
	}

	public void ajouterUneTransition(Etat state, String Etiquet, Etat suivant) throws ValidationException {
		transitionTable.AjouterTransition(state, Etiquet, suivant);
	}

	public ArrayList<Etat> getStates() {
		return states;
	}

	public void setStates(ArrayList<Etat> states) {
		this.states = states;
	}

	public Etat getInitialState() {
		return initialState;
	}

	public void setInitialState(Etat initialState) {
		initialState.setIsIntials();
		this.initialState = initialState;
	}

	public ArrayList<Etat> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(ArrayList<Etat> finalStates) {
		this.finalStates = finalStates;
	}

	public String[] getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String[] alphabet) {
		this.alphabet = alphabet;
	}

	public Transitions getTransitionTable() {
		return transitionTable;
	}

	public void setTransitionTable(Transitions transitionTable) {
		this.transitionTable = transitionTable;
	}

}

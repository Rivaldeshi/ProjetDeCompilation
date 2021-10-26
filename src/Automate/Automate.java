package Automate;

import java.util.ArrayList;

public class Automate {

	private ArrayList<Etat> states;
	private Etat initialState;

	private ArrayList<Etat> finalStates;

	private ArrayList<String> alphabet;

	private Transitions transitionTable;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		this.initialState = initialState;
	}

	public ArrayList<Etat> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(ArrayList<Etat> finalStates) {
		this.finalStates = finalStates;
	}

	public ArrayList<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}

	public Transitions getTransitionTable() {
		return transitionTable;
	}

	public void setTransitionTable(Transitions transitionTable) {
		this.transitionTable = transitionTable;
	}

}

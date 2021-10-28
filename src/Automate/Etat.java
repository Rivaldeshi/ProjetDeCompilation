package Automate;

public class Etat {

	int State;

	public Etat(int state) {
		this.State = state;
	}

	public String toString() {
		return "q" + this.State;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Etat)) {
			return false;
		}
		Etat etat = (Etat) o;

		return State == etat.State;
	}

}
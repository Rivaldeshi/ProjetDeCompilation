package Automate;

public class Etat {

	private int State;
	private boolean isFinal = false;
	private boolean isInitial = false;
	
	
	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	

	public Etat(int state) {
		this.State = state;
	}
    
	public Etat SetState(int state){
		this.State= state;
		return this;
	}
	public String toString() {
		if (isFinal && isInitial) {
			return "q"+ this.State+"(I,F)" ;
		}

		if (isFinal) {
			return "q"+ this.State+"(F)";
		}
		if (isInitial) {
			return "q"+ this.State+"(I)";
		}
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

	public boolean isFinal() {
		return isFinal;
	}

	public void setIsFinal() {
		this.isFinal = true;
	}

	public void setIsIntials() {
		this.isInitial = true;
	}

	public void setFinal(boolean idFinal) {
		this.isFinal = idFinal;
	}

	public boolean isInitial() {
		return isInitial;
	}

	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

}
package View;

import Automate.Automate;
import SwingComponent.Frame;

@SuppressWarnings("serial")
public class MainView extends Frame {

	public static Automate AutomateCourant;
	public static Menu menu = new Menu("hello");
	public static Verification verif = new Verification();

	public MainView() {
		super(verif, menu);

	}
}

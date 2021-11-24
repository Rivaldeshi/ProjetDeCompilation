package View;

import Automate.Automate;
import SwingComponent.Frame;

public class MainView {

	public Frame frame;
	public static Automate AutomateCourant;
	public static Menu menu = new Menu("hello");

	public MainView() {

		frame = new Frame();
		Verification verif = new Verification();
		frame = new Frame(verif, menu);
		frame.pack();
		frame.setVisible(true);

	}
}
